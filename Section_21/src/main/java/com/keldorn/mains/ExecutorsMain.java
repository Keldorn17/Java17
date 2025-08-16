package main.java.com.keldorn.mains;

import main.java.com.keldorn.enums.ThreadColor;

import java.util.List;
import java.util.concurrent.*;

class ColorThreadFactory implements ThreadFactory {
    private String threadName;
    private int colorValue = 1;

    public ColorThreadFactory(ThreadColor color) {
        this.threadName = color.name();
        ;
    }

    public ColorThreadFactory() {
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String name = threadName;
        if (name == null) {
            name = ThreadColor.values()[colorValue].name();
        }
        if (++colorValue > (ThreadColor.values().length - 1)) {
            colorValue = 1;
        }
        thread.setName(name);
        return thread;
    }
}

public class ExecutorsMain {
    public static void main(String[] args) {
        List<Callable<Integer>> taskList = List.of(
                () -> ExecutorsMain.sum(1, 10, 1, "red"),
                () -> ExecutorsMain.sum(10, 100, 10, "blue"),
                () -> ExecutorsMain.sum(2, 20, 2, "green")
        );
        try (var multiExecutor = Executors.newCachedThreadPool()) {
            var results = multiExecutor.invokeAll(taskList);
            for (Future<Integer> result : results) {
                System.out.println(result.get(500, TimeUnit.MILLISECONDS));
            }
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cachedMain(String[] args) {
        try (var multiExecutor = Executors.newCachedThreadPool()) {
            var redValue = multiExecutor.submit(() -> ExecutorsMain.sum(1, 10, 1, "red"));
            var blueValue = multiExecutor.submit(() -> ExecutorsMain.sum(10, 100, 10, "blue"));
            var greenValue = multiExecutor.submit(() -> ExecutorsMain.sum(2, 20, 2, "green"));

            try {
                System.out.println(redValue.get(500, TimeUnit.SECONDS));
                System.out.println(blueValue.get(500, TimeUnit.SECONDS));
                System.out.println(greenValue.get(500, TimeUnit.SECONDS));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void fixedMain(String[] args) {
        final int count = 6;
        try (var multiExecutor = Executors.newFixedThreadPool(
                count, new ColorThreadFactory()
        )) {
            for (int i = 0; i < count; i++) {
                multiExecutor.execute(ExecutorsMain::countDown);
            }
        }

    }

    public static void singleMain(String[] args) {
//        try (var blueExecutor = Executors.newSingleThreadExecutor(
//                new ColorThreadFactory(ThreadColor.ANSI_BLUE)
//        )) {
//            blueExecutor.execute(ExecutorsMain::countDown);
//        }

        var blueExecutor = Executors.newSingleThreadExecutor(
                new ColorThreadFactory(ThreadColor.ANSI_BLUE)
        );
        blueExecutor.execute(ExecutorsMain::countDown);
        blueExecutor.shutdown();

        boolean isDone;
        try {
            isDone = blueExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (isDone) {
            System.out.println("Blue finished, starting Yellow");
            var yellowExecutor = Executors.newSingleThreadExecutor(
                    new ColorThreadFactory(ThreadColor.ANSI_YELLOW)
            );
            yellowExecutor.execute(ExecutorsMain::countDown);
            yellowExecutor.shutdown();
            try {
                isDone = yellowExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (isDone) {
                System.out.println("Yellow finished, starting Red");
                var redExecutor = Executors.newSingleThreadExecutor(
                        new ColorThreadFactory(ThreadColor.ANSI_RED)
                );
                redExecutor.execute(ExecutorsMain::countDown);
                redExecutor.shutdown();
                try {
                    isDone = redExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (isDone) {
                    System.out.println("All processes completed");
                }
            }
        }


    }

    public static void notMain(String[] args) {
        Thread blue = new Thread(ExecutorsMain::countDown, ThreadColor.ANSI_BLUE.name());
        Thread yellow = new Thread(ExecutorsMain::countDown, ThreadColor.ANSI_YELLOW.name());
        Thread red = new Thread(ExecutorsMain::countDown, ThreadColor.ANSI_RED.name());

        blue.start();
        yellow.start();
        red.start();

        try {
            blue.join();
            yellow.join();
            red.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static void countDown() {
        String threadName = Thread.currentThread().getName();
        var threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf(threadName.toUpperCase());
        } catch (IllegalArgumentException ignore) {
        }

        String color = threadColor.color();
        for (int i = 20; i >= 0; i--) {
            System.out.println(color + " " +
                               threadName.replace("ANSI_", "") + " " + i);
        }
    }

    private static int sum(int start, int end, int delta, String colorString) {
        var threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf("ANSI_" + colorString.toUpperCase());
        } catch (IllegalArgumentException ignore) {
        }

        String color = threadColor.color();
        int sum = 0;
        for (int i = start; i <= end; i += delta) {
            sum += i;
        }

        System.out.println(color + Thread.currentThread().getName() + ", " +
                           colorString + " " + sum);
        return sum;
    }
}
