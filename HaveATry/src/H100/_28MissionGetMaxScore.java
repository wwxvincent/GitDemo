package H100;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/27/24
 * @Description:
 */
public class _28MissionGetMaxScore {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int T = in.nextInt();

        PriorityQueue<Task> minHeap = new PriorityQueue<>(
                (t1, t2) -> t1.deadline == t2.deadline ? t2.score - t1.score : t1.deadline - t2.deadline
        );

        for (int i = 0; i < num; i++) {
            int deadline = in.nextInt();
            int score = in.nextInt();
            minHeap.offer(new Task(deadline, score));
        }
        in.close();

        int time = 1;
        int maxScore=0;

        // 使用最小堆 处理任务
        while ( time <= T && minHeap.isEmpty()) {
            Task currenTask = minHeap.poll();
            if (time <= currenTask.score) {
                maxScore += currenTask.score;
                time++;
            }

        }

        System.out.println(maxScore);
    }

    static class Task {
        int deadline; // 最晚处理时间
        int score;

        Task (int deadline, int score) {
            this.deadline = deadline;
            this.score = score;
        }
    }
}
