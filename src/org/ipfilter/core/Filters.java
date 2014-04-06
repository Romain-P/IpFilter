package org.ipfilter.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.ipfilter.filters.Filter;
import org.ipfilter.filters.SafeFilter;
import org.ipfilter.filters.UnsafeFilter;
import org.ipfilter.injector.SafeModule;

import java.util.concurrent.TimeUnit;

public class Filters {
    public static Filter createNewUnsafe(long connections, long time, TimeUnit unit) {
        return new UnsafeFilter().with(connections, time, unit);
    }

    public static Filter createNewSafe(long connections, long time, TimeUnit unit) {
        Injector injector = Guice.createInjector(new SafeModule());
        return injector.getInstance(SafeFilter.class).with(connections, time, unit);
    }
}
