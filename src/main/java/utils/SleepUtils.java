package utils;

import java.util.concurrent.TimeUnit;

public class SleepUtils {

    public static void sleep(int timeToSleep) {
        try {
            TimeUnit.SECONDS.sleep(timeToSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
