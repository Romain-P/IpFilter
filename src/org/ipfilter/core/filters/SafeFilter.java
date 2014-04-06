package org.ipfilter.core.filters;

import com.google.inject.Inject;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SafeFilter implements Filter {
    private ReentrantLock locker;
    @Inject UnsafeFilter filter;

    public SafeFilter() {
        this.locker = new ReentrantLock();
    }

    public Filter with(long max, long time, TimeUnit unit) {
        filter.setMaxConnections(max);
        filter.setRestrictedTime(TimeUnit.MILLISECONDS.convert(time, unit));
        return this;
    }

    @Override
    public boolean authorizes(String ip) {
        try {
            locker.lock();
            return filter.authorizes(null);
        } catch(Exception e) {} finally {
            locker.unlock();
        }
        return false;
    }
}
