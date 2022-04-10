package writerReaderProblem;

import java.util.Random;

/**
 * in this function we call object to make readers and writers,
 * and then they start to read and (in this scenario increment) the value
 */
public class RunExample {
    public static void main(String[] args) {
        Random random = new Random();
        WriterPreference wp = new WriterPreference();
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                while (true) {
                    wp.acquireWrite();
                    wp.incrementInt();
                    System.out.println("\t\t\t\t\tWriter " + finalI + " incremented");
                    wp.releaseWrite();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                    }
                }
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                while (true) {
                    wp.acquireRead();
                    System.out.println("reader " + finalI + ": " + wp.readInt());
                    wp.releaseRead();
                    try {
                        Thread.sleep(random.nextInt(2500));
                    } catch (InterruptedException ignored) {
                    }
                }
            }).start();
        }
    }
}
