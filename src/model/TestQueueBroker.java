package model;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class TestQueueBroker extends Thread {

    private volatile static TestQueueBroker BROKER = new TestQueueBroker();
    private volatile static TestQueue QUEUE = new TestQueue();
    private static boolean isAlive = false;

    @Override
    public void run() {
        isAlive = true;
        while (isAlive) {
            Pair<LocalDateTime, Callable> poll = QUEUE.poll();
            if (poll != null) {
                try {
                    poll.getValue().call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void finish() {
        isAlive = false;
    }

    public synchronized void add(Pair<LocalDateTime, Callable> pair) {
        QUEUE.add(pair);
    }

    public static TestQueueBroker getInstance() {
        return BROKER;
    }
}
