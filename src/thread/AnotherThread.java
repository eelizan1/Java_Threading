package thread;/*
 * Created by eeliz_000 on 5/4/2017.
 */
import static thread.ThreadColor.ANSI_BLUE;

public class AnotherThread extends Thread {
    @Override
    public void run() {
        System.out.println(ANSI_BLUE + "Hello from another thread");
    }
}
