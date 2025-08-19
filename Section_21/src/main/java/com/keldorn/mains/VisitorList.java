package main.java.com.keldorn.mains;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class VisitorList {
    private static final CopyOnWriteArrayList<Person> masterList;
    private static final ArrayBlockingQueue<Person> newVisitors =
            new ArrayBlockingQueue<>(5);

    static {
        masterList = Stream.generate(Person::new)
                .distinct()
                .limit(250)
                .collect(CopyOnWriteArrayList::new,
                        CopyOnWriteArrayList::add,
                        CopyOnWriteArrayList::addAll);
    }

    public static void main(String[] args) {
        Runnable producer = () -> {
            Person visitor = new Person();
            System.out.println("Queueing " + visitor);
            boolean queued = false;
            try {
                queued = newVisitors.offer(visitor, 5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println(e.getClass().getSimpleName());
            }
            if (queued) {
//                System.out.println(newVisitors);
            } else {
                System.out.println("Queue is Full, cannot add " + visitor);
                System.out.println("Draining Queue and writing data to file");
                List<Person> tempList = new ArrayList<>();
                newVisitors.drainTo(tempList);
                List<String> lines = new ArrayList<>();
                tempList.forEach(customer -> lines.add(customer.toString()));
                lines.add(visitor.toString());

                try {
                    Files.createDirectories(Path.of("./files"));
                    Files.write(Path.of("./files/DrainedQueue.txt"), lines,
                            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable consumer = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " Polling queue " + newVisitors.size());
            Person visitor;
            try {
                visitor = newVisitors.poll(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (visitor != null) {
                System.out.println(threadName + " " + visitor);
                if (!masterList.contains(visitor)) {
                    masterList.add(visitor);
                    System.out.println("--> New Visitor gets Coupon!: " + visitor);
                }
            }
            System.out.println(threadName + " done " + newVisitors.size());
        };

        try (ScheduledExecutorService producerExecutor =
                Executors.newSingleThreadScheduledExecutor();
             ScheduledExecutorService consumerPool =
                     Executors.newScheduledThreadPool(3)) {
            producerExecutor.scheduleWithFixedDelay(producer, 0, 3, TimeUnit.SECONDS);

            for (int i = 0; i < 3; i++) {
                consumerPool.scheduleAtFixedRate(consumer, 6, 1, TimeUnit.SECONDS);
            }

            while (true) {
                try {
                    if (!producerExecutor.awaitTermination(10, TimeUnit.SECONDS))
                        break;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            while (true) {
                try {
                    if (!consumerPool.awaitTermination(3, TimeUnit.SECONDS))
                        break;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
