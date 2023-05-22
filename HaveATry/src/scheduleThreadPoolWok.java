import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class scheduleThreadPoolWok {


    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPool = new ScheduledThreadPoolExecutor(2);
        System.out.println("task begin: " + System.currentTimeMillis()/1000);
        agent a = new agent();
        scheduledThreadPool.scheduleAtFixedRate(a,2,3, TimeUnit.SECONDS);
        //Instance s = new Instance();
    }

    static class agent implements Runnable {


        @Override
        public void run() {
            try{
                Thread.sleep(2000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"--tasl run: "+ System.currentTimeMillis());
        }
    }
}
