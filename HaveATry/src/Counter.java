/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 6/26/24
 * @Description: volatile thread not safe example
 */
public class Counter {
    private volatile int count = 0;

    public void increment() {
        count++; // 非原子操作
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        // 创建多个线程来递增计数器
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            thread.join();
        }

        // 期望count的值是10000，但由于线程不安全，实际值可能小于10000
        System.out.println("Final count: " + counter.getCount());
    }
}
// 为什么不是tread safe，因为（count++）不是一个原子操作
// 它实际上包含了三个步骤：
// 1。 读取count的值到寄存器。
// 2。 将寄存器的值加到1。
// 3。 将寄存器中更新后的值写回count。
