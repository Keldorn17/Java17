package main.java.com.keldorn.challenges.challenge1;

import java.util.concurrent.TimeUnit;

public class OddRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 10; i <= 20; i++) {
            if (i % 2 != 0) {
                System.out.println(getClass().getSimpleName() + ": " + i);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(getClass().getSimpleName() + " interrupted");
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
