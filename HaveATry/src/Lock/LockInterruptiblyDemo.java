package Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/23/24
 * @Description:
 */
public class LockInterruptiblyDemo {
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) throws Throwable {
        /* 可中断锁*/
        Thread t1 = new Thread(new ReentrantLockThread(lock1, lock2) );
        Thread t2 = new Thread(new ReentrantLockThread(lock2, lock1) );
        t1.start();
        t2.start();
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("祝线程开始睡第一秒"); // 主线程睡眠1秒，避免线程t1直接相应run方法中的睡眠中断
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("主线程， 在" + t1.getName() + "上开始执行interrupt()");
        t1.interrupt(); // 中断锁
    }

    /**
     * ReentrantLock 的 lockInterruptibly 实现死锁
     */
    static class ReentrantLockThread implements Runnable {
        private ReentrantLock lock1, lock2;

        public ReentrantLockThread(ReentrantLock lock1, ReentrantLock lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            try {
                // 对 lock1 加锁
                lock1.lockInterruptibly(); // 获取lock1的可中断锁
                System.out.println(Thread.currentThread().getName() + ", 加锁成功1-2！");
                TimeUnit.MILLISECONDS.sleep(100); // 等待lock1和lock2分别被两个线程获取，产生死锁现象
                // 对 lock2 加锁
                lock2.lockInterruptibly(); // 获取lock1的可中断锁
                System.out.println(Thread.currentThread().getName() + ", 加锁成功2-2！");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + ", 发生异常！");
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }
                System.out.println(Thread.currentThread().getName() + ", 解锁成功！");
            }
        }
    }
}
