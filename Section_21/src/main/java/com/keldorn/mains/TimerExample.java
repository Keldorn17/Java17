package main.java.com.keldorn.mains;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TimerExample {
    public static void main(String[] args) {
//        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                System.out.println(threadName + " Timer task executed at: " +
                                   formatter.format(LocalDateTime.now()));
            }
        };

//        timer.scheduleAtFixedRate(task, 0, 2000);
//
//        try {
//            Thread.sleep(12000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        timer.cancel();

        try (var executor = Executors.newSingleThreadScheduledExecutor()) {
            executor.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
            TimeUnit.SECONDS.sleep(12);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
