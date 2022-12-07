import java.util.concurrent.RecursiveTask;

public class ComplicatedTask extends RecursiveTask<Integer> {
    int[] ia;
    int from;
    int to;
    static final int THRESHOLD = 3;

    public ComplicatedTask(int[] ia, int from, int to) {
        this.ia = ia;
        this.from = from;
        this.to = to;
    }


    protected Integer compute() {
        int sum = 0;
        if (from + THRESHOLD > to) {
            for(int i = from; i <= to; i++) {
                sum = sum +ia[i];
            }
        } else {
            int mid = (from+to) /2;
            ComplicatedTask t1 = new ComplicatedTask(ia, from, mid);
            ComplicatedTask t2 = new ComplicatedTask(ia, mid+1, to);
            t2.fork();
            t1.compute();
            t2.join();
        }
        return null;
    }
}
