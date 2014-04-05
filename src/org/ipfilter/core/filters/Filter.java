package org.ipfilter.core.filters;

/**
 * Created by Return on 05/04/14.
 */
public interface Filter {
    public boolean authorizes(String ip);
}
