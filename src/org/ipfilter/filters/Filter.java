package org.ipfilter.filters;

import java.util.concurrent.TimeUnit;

public interface Filter {
    public boolean authorizes(String ip);
    public Filter with(long connections, long time, TimeUnit unit);
}
