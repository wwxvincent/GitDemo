package H200;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/31/24
 * @Description:
 */
public class _04TaskDeal {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int maxDay = 0;
//        List<List<Integer>> list = new ArrayList<>();
//        for (int i = 0; i<n; i++) {
//            int start = in.nextInt();
//            int end = in.nextInt();
//            list.set(start, new ArrayList<>());
//            list.get(start).add(end);
//            maxDay = Math.max(maxDay, end);
//        }
//        int[][] arr = new int[n][2];
//        for (int i = 0; i < n; i++) {
//            arr[i][0] = in.nextInt();
//            arr[i][1] = in.nextInt();
//            maxDay = Math.max(maxDay, arr[i][1]);
//        }
        List<Integer>[] list = new List[100005];
        for (int i = 0; i< list.length; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            maxDay = Math.max(maxDay, end);
            list[start].add(end);
        }
        // minheap by ending day
        int res=0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 1; i <= maxDay; i++) {
            for (int endDay : list[i]) {
                    minHeap.add(endDay);
            }
            while (!minHeap.isEmpty() && minHeap.peek() < i) {
                minHeap.poll();
            } // 如果堆顶 是小于 deadline的，就pop出去，因为也做不了了
            if (!minHeap.isEmpty()) { // 然后 pop 最上面一个，做一个任务
                res++;
                minHeap.poll();
            }
        }
        System.out.println(res);
    }
}
