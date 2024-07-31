/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 6/26/24
 * @Description:
 */
public class DeadLockExample {
    // 定义两个资源
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            // 线程1首先尝试获取resource1的锁
            synchronized (resource1) {
                System.out.println("Thread 1: acquired resource 1");

                try {
                    // 稍作等待，让线程2获得执行机会
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 然后尝试获取resource2的锁
                synchronized (resource2) {
                    System.out.println("Thread 1: acquired resource 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            // 线程2首先尝试获取resource2的锁
            synchronized (resource2) {
                System.out.println("Thread 2: acquired resource 2");

                try {
                    // 稍作等待，让线程1获得执行机会
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 然后尝试获取resource1的锁
                synchronized (resource1) {
                    System.out.println("Thread 2: acquired resource 1");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
