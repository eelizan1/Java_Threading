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
        // created a new thread from MyRunnable - passed through as a parameter
//        Thread myRunnableThread = new Thread(new MyRunnable());
//        myRunnableThread.start();

        // creating an anonymous class with runnable
        Thread myRunnableThread = new Thread(new MyRunnable() {
            @Override
            public void run() {
                // executes the run method from the MyRunnable class
                super.run();
                // System.out.println(ANSI_RED + "Hello from the anonymous class's implementation of run()");
            }
        });

        myRunnableThread.start();
        System.out.println(ANSI_PURPLE + "Hello again from the main thread");
    }
}
