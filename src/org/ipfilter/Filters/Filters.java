package org.ipfilter.Filters;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.ipfilter.injector.SafeModule;

/**
 * Created by Return on 05/04/14.
 */
public class Filters {
    public Filter createNewUnsafe() {
        return new UnsafeFilter();
    }

    public Filter createNewSafe() {
        Injector injector = Guice.createInjector(new SafeModule());
        return injector.getInstance(SafeFilter.class);
    }
}
