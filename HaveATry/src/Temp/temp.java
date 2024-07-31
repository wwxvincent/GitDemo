package Temp;

import java.util.*;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/30/24
 * @Description:
 */
public class temp {



        public static boolean canDistribute(int[] tasks, int n, int maxLoad) {
            Arrays.sort(tasks);
            int[] loads = new int[n];
            return backtrack(tasks, loads, tasks.length - 1, n, maxLoad, new HashSet<>());
        }

        public static boolean backtrack(int[] tasks, int[] loads, int index, int n, int maxLoad, HashSet<String> seen) {
            if (index < 0) {
                return true;
            }

            String key = Arrays.toString(loads);
            if (seen.contains(key)) {  // 检查是否已经探索过相同的负载配置
                return false;
            }
            seen.add(key);

            for (int i = 0; i < n; i++) {
                if (loads[i] + tasks[index] <= maxLoad) {
                    loads[i] += tasks[index];
                    if (backtrack(tasks, loads, index - 1, n, maxLoad, seen)) {
                        return true;
                    }
                    loads[i] -= tasks[index];
                }
                // 如果当前负载为0，不需要尝试更多，因为任何更多的分配都会产生相同的结果
                if (loads[i] == 0) break;
            }
            return false;
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // 输入任务，假设任务之间以空格分隔
            String[] taskStrings = scanner.nextLine().split(" ");
            int[] tasks = Arrays.stream(taskStrings).mapToInt(Integer::parseInt).toArray();

            // 输入开发人员数量
            int n = scanner.nextInt();

            int left = Arrays.stream(tasks).max().getAsInt();
            int right = Arrays.stream(tasks).sum();
            int result = right;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (canDistribute(tasks, n, mid)) {
                    result = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            // 输出最小的最大工作量
            System.out.println(result);
        }
    }

// index from back to the front
// 先 分一半出去，作为一个 局部，和 剩余的
// 6 2 7 7 9 3 2 1 3 11 4
// 1 2 2 3 3 4 6 7 7 9 11

// G1
// 1  3

// G2
// 2

// G3
// 2