package producer_consumer_messages;

import java.util.Random;

/*
 * Created by eeliz_000 on 5/6/2017.
 *
 *  Process:
 *      Each thread waits and releases it's lock on the message object when the
 *      loop condition passes and that gives the other thread the opportunity to run
 *      It can now process a message and change the value empty variable. When it calls
 *      the notifyAll method the thread that's waiting can now proceed.
 *
 *      Two threads go back and forth until all the messages have been printed
 */
public class Main {
    public static void main (String[] args) {
        // Shared message object
        Message message = new Message();
        // code to create and run these threads
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();
    }
}

/*
 Synchronize the read and write methods because when a thread is running
 one of these methods, we don't want the other thread to be able to run either method

 ex. We don't run the reader thread to run while the writer thread is writing a message and
 vice versa

 note: Only ONE synchronized method can run at a time or else one will block the other

 */
class Message {
    // shared by producer consumer
    private String message;
    private boolean empty = true;

    // used by consumer
    // message.read
    public synchronized String read() {
        // if there's no message to read
        while(empty) {
            /*
                when a thread calls a wait method, it will suspend execution
                an release the locks it'ls holding until another thread issues
                a notification that something important has happened - other thread issues a notification
             */
            try {
                wait();
            } catch(InterruptedException e) {

            }
        }
        // set to true to indicate that consumer has read the message
        empty = true;
        // call notify after thread changes the value of empty
        notifyAll();
        return message;
    }

    // used by producer
    // message.write
    public synchronized void write(String message) {
        // want the consumer to ead each message before we write another one
        // so check if empty before writing
        while(!empty) {
            try {
                wait();
            } catch(InterruptedException e) {

            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
}

// writer thread (producer)
class Writer implements Runnable {
    // the message object
    private Message message;

    // stores the message object
    public Writer(Message message) {
        this.message = message;
    }

    // run method
    public void run () {
        // the array of messages
        String messages [] = {
            "Humpty Dumpt sat on a wall",
            "Humpty Dumpty had a great fall",
            "All the kings horses and all the kings men",
            "Couldnt ou Humpty together again"
        };

        // Random delay
        Random random = new Random();

        // loop through messages
        for (int i = 0; i < messages.length; i++) {
            message.write(messages[i]);
            try {
                // sleep up to two seconds randomly after writing a message
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                // catch if interrupted
            }
        }
        message.write("Finished");
    }
}

// reader thread (consumer)
class Reader implements Runnable {
    private Message message;

    public Reader(Message message) {
        // receive message object and save in field
        this.message = message;
    }

    public void run() {
        Random random = new Random();
        // wait for message to be "Finished" to be received
        // loop through messages that we're received
        for(String latestMessage = message.read(); !latestMessage.equals("Finished"); latestMessage = message.read()) {
            System.out.println(latestMessage);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                // catch interruption
            }
        }
    }
}

