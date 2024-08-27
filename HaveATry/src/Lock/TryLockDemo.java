//package Lock;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * @Author: Vincent(Wenxuan) Wang
// * @Date: 8/23/24
// * @Description:
// */
//public class TryLockDemo {
//    public static void main(String[] args) {
//        Task task = new Task();
//        new Thread(task).start();
//        new Thread(task).start();
//    }
//}
//
//@Slf4j
//class Task implements Runnable {
//    public static ReentrantLock lock = new ReentrantLock();
//
//    @Override
//    public void run() {
//        String threadName = Thread.currentThread().getName();
//        try {
//            if (lock.tryLock(1, TimeUnit.SECONDS)) {
//                System.out.println(System.currentTimeMillis() + " " + threadName + " lock!");
//                Thread.sleep(3000);
//            } else {
//                System.out.println(System.currentTimeMillis() + " " + threadName + " lock failure!");            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            if (lock.isHeldByCurrentThread()) {
//                System.out.println(System.currentTimeMillis() + " " + threadName + " unlock!");            }
//            }
//        }
//    }
//}
