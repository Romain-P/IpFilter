package test.org.test.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.ipfilter.filters.Filter;
import org.ipfilter.filters.SafeFilter;
import org.ipfilter.filters.UnsafeFilter;
import org.ipfilter.injector.FilterModule;

import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new FilterModule());
        //creating the game ^_^
        new Game(injector);
    }
}

class Game {
    //1 connection authorized in 1 second, else ip is blocked
    private Filter filter;// = new Filters().createNewUnsafe(1, 1, TimeUnit.SECONDS);
    private Filter safeFilter;// = new Filters().useSafe(1, 1, TimeUnit.SECONDS);

    public Game(Injector injector) {
        this.filter = new UnsafeFilter().with(1, 1, TimeUnit.SECONDS);

        //a fucking safe filter :]
        this.safeFilter = injector.getInstance(SafeFilter.class).with(1, 1, TimeUnit.SECONDS);
    }
    //use safe filter when filter is used by some threads
    //and use unsafe when filter is called by only one thread.

    //unsafe example
    //imagine while(true) .. socket.accept.. Game.newConnection();
    public void newConnection(String ip, Socket socket) {
        if(filter.authorizes(ip)) { //or socket.getInetAddress() ..
            //connection authorized, it can connect to the game
        } else {
            //do something..
            //example: socket.close();
        }
    }
}
