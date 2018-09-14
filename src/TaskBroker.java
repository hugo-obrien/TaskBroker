import javafx.util.Pair;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TaskBroker {

    private static final TaskBroker BROKER = new TaskBroker();
    private static final int THREAD_POOL_SIZE = 5;
    private static final ScheduledExecutorService THREAD_POOL = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);

    public synchronized Future addTask(Pair<LocalDateTime, Callable> pair) {
        return addTask(pair.getValue(), getDelay(pair.getKey()));
    }

    public synchronized Future addTask(LocalDateTime dateTime, Callable task) {
        return addTask(task, getDelay(dateTime));
    }

    public synchronized List<Future> addBacklog(List<Pair<LocalDateTime, Callable>> backlog) {
        List<Future> futures = new ArrayList<>();
        for (Pair<LocalDateTime, Callable> pair : backlog) {
            futures.add(addTask(pair));
        }
        return futures;
    }

    public static TaskBroker getInstance() {
        return BROKER;
    }

    private long getDelay(LocalDateTime startTime) {
        return LocalDateTime.now().until(startTime, ChronoUnit.NANOS);
    }

    private Future addTask(Callable task, long delay) {
        return THREAD_POOL.schedule(task, delay, TimeUnit.NANOSECONDS);
    }
}
