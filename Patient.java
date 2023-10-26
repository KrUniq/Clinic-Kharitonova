import java.util.concurrent.Semaphore;

class Patient implements Runnable {
    private int id;
    private Semaphore therapistSemaphore;
    private Semaphore mrtSemaphore;

    public Patient(int id, Semaphore therapistSemaphore, Semaphore mriSemaphore) {
        this.id = id;
        this.therapistSemaphore = therapistSemaphore;
        this.mrtSemaphore = mriSemaphore;
    }
    @Override
    public void run() {
        try {
            // Пациент приходит и ждет приема у терапевта
            therapistSemaphore.acquire();
            System.out.println("Пациент " + id + " идет к терапевту");
            Thread.sleep(1000); // осмотр у терапевта
            System.out.println("Пациент " + id + " завершил прием у терапевта");

            // После терапевта пациент отправляется на МРТ
            mrtSemaphore.acquire();
            System.out.println("Пациент " + id + " идет на МРТ");
            Thread.sleep(1000); // Симуляция МРТ
            System.out.println("Пациент " + id + " завершил МРТ и уходит");

            mrtSemaphore.release();
            therapistSemaphore.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}