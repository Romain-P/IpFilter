package test.org.test.core;

import org.ipfilter.Filters.Filter;
import org.ipfilter.core.Filters;

/**
 * Created by Return on 05/04/14.
 */
public class Main {
    public static void main(String[] args) {
        //creating the game ^_^
        new Game();
    }
}

class Game {
    private Filter filter = Filters.createNewUnsafe();

    public void newConnection(String ip) {
        if(filter.authorizes(ip)) {
            //connection authorized, it can connect to the game
        } else {
            //do something..
            //example: socket.close();
        }
    }
}
