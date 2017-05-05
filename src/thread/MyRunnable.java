package thread;

import static thread.ThreadColor.ANSI_RED;

/*
 * Created by eeliz_000 on 5/5/2017.
 */
public class MyRunnable implements Runnable {
    // need to override
    @Override
    public void run() {
        System.out.println(ANSI_RED + "Hello from MyRunnable's run()");
    }
}
