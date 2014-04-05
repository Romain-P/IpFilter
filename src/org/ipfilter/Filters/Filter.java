package org.ipfilter.Filters;

/**
 * Created by Return on 05/04/14.
 */
public interface Filter {
    public boolean authorizes(String ip);
}
