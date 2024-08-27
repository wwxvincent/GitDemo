package Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/23/24
 * @Description:
 */
public class ReentrantLockDemo1 {
    public static void main (String[] args) {
        new Thread(new Task()).start();
    }

    static class Task implements Runnable {
        public ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " is locked!");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " is locked!");
            try {
                System.out.println(Thread.currentThread().getName() + " is coming!");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " is unlocked!");
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " is unlocked!");
//                lock.unlock();
//                System.out.println(Thread.currentThread().getName() + " is unlocked!");
            }
        }
    }
}
