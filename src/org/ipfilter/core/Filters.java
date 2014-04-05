package org.ipfilter.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.ipfilter.Filters.Filter;
import org.ipfilter.Filters.SafeFilter;
import org.ipfilter.Filters.UnsafeFilter;
import org.ipfilter.injector.SafeModule;

/**
 * Created by Return on 05/04/14.
 */
public class Filters {
    public static Filter createNewUnsafe() {
        return new UnsafeFilter();
    }

    public static Filter createNewSafe() {
        Injector injector = Guice.createInjector(new SafeModule());
        return injector.getInstance(SafeFilter.class);
    }
}
