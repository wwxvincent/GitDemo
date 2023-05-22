import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class threadWok implements Runnable{

    private String command;

    public threadWok(String s) {
        this.command=s;
    }


    public static void main (String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            Runnable worker = new threadWok(""+i);
            executorService.execute(worker);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()){

        }
        System.out.println("Finished all threads");
    }



    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start. command = " + command);
        processCommand();
        System.out.println(Thread.currentThread().getName()+" End. ");
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }
}
