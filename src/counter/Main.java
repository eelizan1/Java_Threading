package counter;

/*
 * Created by eeliz_000 on 5/5/2017.
 */

// two threads counting down from 10 using a countdown object with synchronization
public class Main {
    public static void main (String[] args) {
        Countdown countdown = new Countdown();
        // instances of CountdownThread class
        CountdownThread thread1 = new CountdownThread(countdown);
        thread1.setName("Thread 1");
        CountdownThread thread2 = new CountdownThread(countdown);
        thread2.setName("Thread 2");

        // initiate threads
        thread1.start();
        thread2.start();
    }
}

// Countdown class
class Countdown {
    private int i;
    // synchronized - allows thread 1 to finish then proceed to thread 2 which will prevent race conditions with i
    public synchronized void doCountdown() {
        String color;
        switch(Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }
        // the count down
        for (i = 10; i > 0; i--) {
            System.out.println(color + Thread.currentThread().getName() + ": " + i);
        }
    }
}

// Thread class
class CountdownThread extends Thread {
    private Countdown threadCountdown;
    // constructor
    public CountdownThread(Countdown countdown) {
        // assign value to field
        threadCountdown = countdown;
    }

    // run method
    public void run() {
        // Countdown class method: line 16
        threadCountdown.doCountdown();
    }
}
