import java.util.Random;
import java.util.concurrent.Semaphore;

public class Clinic {
    public static void main(String[] args) {
        int numPatients = new Random().nextInt(2,12);
        Semaphore therapistSemaphore = new Semaphore(1); // Семафор для терапевта
        Semaphore mrtSemaphore = new Semaphore(1); // Семафор для кабинета МРТ

        int maxQueueLength = numPatients-1;
        for (int i = 1; i < numPatients; i++) {
            new Thread(new Patient(i, therapistSemaphore, mrtSemaphore)).start();
        }
        System.out.println("Максимальная длина очереди: " + maxQueueLength);
    }
}