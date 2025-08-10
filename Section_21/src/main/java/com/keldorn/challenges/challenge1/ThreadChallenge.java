package main.java.com.keldorn.challenges.challenge1;

import java.util.concurrent.TimeUnit;

public class ThreadChallenge {
    public static void main(String[] args) {
        EvenThread evenThread = new EvenThread();
        Thread oddThread = new Thread(new OddRunnable());

        evenThread.start();
        oddThread.start();

        var now = System.currentTimeMillis();
        while (oddThread.isAlive() && evenThread.isAlive()) {
            try {
                TimeUnit.SECONDS.sleep(1);
                if (System.currentTimeMillis() - now > 3000) {
//                    evenThread.interrupt();
                    oddThread.interrupt();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
