package org.ipfilter.core.filters;

/**
 * Created by Return on 05/04/14.
 */
public class UnsafeFilter implements Filter {
    @Override
    public boolean authorizes(String ip) {
        //TODO filter
        return false;
    }
}
