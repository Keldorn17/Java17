package main.java.com.keldorn.mains;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class SchedulingTasks {
    public static void main(String[] args) {
        var dtf = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM,
                FormatStyle.LONG
        );

        Callable<ZonedDateTime> waitThenDoIt = () -> {
            ZonedDateTime zdt;
            try {
                TimeUnit.SECONDS.sleep(2);
                zdt = ZonedDateTime.now();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return zdt;
        };

        List<Callable<ZonedDateTime>> list = Collections.nCopies(4, waitThenDoIt);
        try (var threadPool = Executors.newFixedThreadPool(2)) {
            System.out.println("--> " + ZonedDateTime.now().format(dtf));
            List<Future<ZonedDateTime>> futureList = threadPool.invokeAll(list);
            for (Future<ZonedDateTime> result : futureList) {
                try {
                    System.out.println(result.get(1, TimeUnit.SECONDS).format(dtf));
                } catch (ExecutionException | TimeoutException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Runnable dateTask = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("a " + ZonedDateTime.now().format(dtf));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        System.out.println("--> " + ZonedDateTime.now().format(dtf));
        try (ScheduledExecutorService executor = Executors.newScheduledThreadPool(4)) {
//            executor.schedule(() ->
//                    System.out.println(ZonedDateTime.now().format(dtf)), 2, TimeUnit.SECONDS);
//            executor.scheduleWithFixedDelay() // schedules After a task is finished
            var scheduledTask = executor.scheduleAtFixedRate(  // schedules without waiting for the task to finish
                    dateTask,
                    2,
                    2,
                    TimeUnit.SECONDS);

            var scheduledTask2 = executor.scheduleAtFixedRate(
                    () -> System.out.println("b " + ZonedDateTime.now().format(dtf)),
                    2,
                    2,
                    TimeUnit.SECONDS
            );
            long time = System.currentTimeMillis();
            while (!scheduledTask.isDone()) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    if ((System.currentTimeMillis() - time) / 1000 > 10) {
                        scheduledTask.cancel(true);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
