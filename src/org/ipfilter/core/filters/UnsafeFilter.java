package org.ipfilter.core.filters;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class UnsafeFilter implements Filter {
    //instances
    private HashMap<String, Instance> instances = new HashMap<>();
    //configuration
    private long restrictedTime, maxConnections;

    public Filter with(long max, long time, TimeUnit unit) {
        this.maxConnections = max;
        this.restrictedTime = TimeUnit.MILLISECONDS.convert(time, unit);
        return this;
    }

    @Override
    public boolean authorizes(String ip) {//a
        Instance instance = find(ip);

        if (instance.isBlocked()) {
            return false;
        } else {
            instance.addConnection();

            if (instance.lastConnection() + this.restrictedTime >= System.currentTimeMillis()) {
                if (instance.getConnections() < this.maxConnections)
                    return true;
                else {
                    instance.block();
                    return false;
                }
            } else {
                instance.updateLastConnection();
                instance.resetConnections();
            }
            return true;
        }
    }

    public Instance find(String ip) {
        ip = ip.contains(":")?ip.split(":")[0]:ip;

        Instance result = this.instances.get(ip);
        if (result != null) return result;

        result = new Instance();
        this.instances.put(ip, result);
        return result;
    }

    public void setMaxConnections(long connections) {
        this.maxConnections = connections;
    }

    public void setRestrictedTime(long time) {
        this.restrictedTime = time;
    }
}

class Instance {
    private long connections;
    private long lastConnection;
    private boolean blocked;

    public Instance() {
        this.connections++;
        this.lastConnection = System.currentTimeMillis();
    }

    public void addConnection() {
        this.connections++;
    }

    public void resetConnections() {
        this.connections = 0;
    }

    public long getConnections() {
        return this.connections;
    }

    public long lastConnection() {
        return this.lastConnection;
    }

    public void updateLastConnection() {
        this.lastConnection = System.currentTimeMillis();
    }

    public boolean isBlocked() {
        return this.blocked;
    }

    public void block() {
        this.blocked = true;
    }
}
