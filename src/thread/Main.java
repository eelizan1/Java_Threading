package thread;/*
 * Created by eeliz_000 on 5/4/2017.
 * SIMPLE DEMONSTRATION OF THREADING
 */
import thread.ThreadColor;

import static thread.ThreadColor.ANSI_GREEN;
import static thread.ThreadColor.ANSI_PURPLE;
import static thread.ThreadColor.ANSI_RED;

// Creating a sub class of the thread and then over-riding it
public class Main {
    public static void main(String[] args) {
        System.out.println(ANSI_PURPLE + "Hello from the main thread");
        // create instance of the thread
        Thread anotherThread = new AnotherThread();
        // use start method to initiate the 'run' method in AnotherThread
        anotherThread.start();
        // creating an anonymous class - used for running things once
        new Thread () {
            public void run() {
                System.out.println(ANSI_GREEN + "Hello from anonymous class thread");
            }
        }.start();
        System.out.println(ANSI_PURPLE + "Hello again from the main thread");
    }
}
