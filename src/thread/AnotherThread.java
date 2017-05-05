package thread;/*
 * Created by eeliz_000 on 5/4/2017.
 */
import static thread.ThreadColor.ANSI_BLUE;

public class AnotherThread extends Thread {
    @Override
    public void run() {
        System.out.println(ANSI_BLUE + "Hello from " + currentThread().getName());
        // thread will sleep for three seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // if another thread interrupts which will stop this thread and have it do something else
            System.out.println(ANSI_BLUE + "Another thread woke me up");
        }
        System.out.println(ANSI_BLUE + "Three seconds have passed and I'm awake");
    }
}
