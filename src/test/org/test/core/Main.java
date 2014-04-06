package test.org.test.core;

import org.ipfilter.core.filters.Filter;
import org.ipfilter.core.Filters;

import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        //creating the game ^_^
        new Game();
    }
}

class Game {
    //1 connection authorized in 1 second, else ip is blocked
    private Filter filter = Filters.createNewUnsafe(1, 1, TimeUnit.SECONDS);
    private Filter safeFilter = Filters.createNewSafe(1, 1, TimeUnit.SECONDS);

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
