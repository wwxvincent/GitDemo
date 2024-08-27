package HCodeFronScatch;

import java.util.Scanner;
import java.util.Stack;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/5/24
 * @Description:
 */
public class _03InsertCharToDelete {

    // 每个位置都插上A B C， 看那个消除的最多
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num =in.nextInt();
        int[] res = new int[num];
        for (int i = 0; i < num; i++) {
            String line = in.next();
            res[i] = solution(line);
        }
        for (int i = 0; i < num; i++) {
            System.out.println(res[i]);
        }
        in.close();
    }

    private static int solution(String line) {
        int sum = 0;
        int maxScore = line.length()+1;

        // 头
        sum = Math.max(sum, delete("A".concat(line)));
        sum = Math.max(sum, delete("B".concat(line)));
        sum = Math.max(sum, delete("C".concat(line)));
        if (sum == maxScore) return maxScore;

        for (int i = 0; i < line.length(); i++) {
            int tempA = delete(line.substring(0,i+1).concat("A").concat(line.substring(i+1)));
            int tempB = delete(line.substring(0,i+1).concat("B").concat(line.substring(i+1)));
            int tempC = delete(line.substring(0,i+1).concat("C").concat(line.substring(i+1)));
            sum = Math.max(tempA, sum);
            sum = Math.max(tempB, sum);
            sum = Math.max(tempC, sum);

            if (sum == maxScore) return maxScore;
        }
        // 尾巴
        sum = Math.max(sum, delete(line.concat("A")));
        sum = Math.max(sum, delete(line.concat("B")));
        sum = Math.max(sum, delete(line.concat("C")));

        return sum;
    }

    private static int delete(String line) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < line.length(); i++) {
            int start = i;
            char cur= line.charAt(i);
            int count = 1;
            while ( i+1 < line.length() && cur == line.charAt(i+1)) {
                count++;
                i++;
            }
            if (count>1 && (  start - 1 < 0 || line.charAt(start-1) != cur)) {
                continue;
            }
            if (stack.size() > 0 && stack.peek() == cur) {
                stack.pop();
            } else {
                if(count == 1) {
                    stack.push(cur);
                }
            }
        }
        int score = line.length() - stack.size();
        return score;
    }
}
