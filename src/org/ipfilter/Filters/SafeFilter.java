package org.ipfilter.Filters;

import com.google.inject.Inject;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Return on 05/04/14.
 */
public class SafeFilter implements Filter {
    private ReentrantLock locker;
    @Inject UnsafeFilter filter;

    public SafeFilter() {
        this.locker = new ReentrantLock();
    }

    @Override
    public boolean authorizes() {
        try {
            locker.lock();
            return filter.authorizes();
        } catch(Exception e) {} finally {
            locker.unlock();
        }
        return false;
    }
}
