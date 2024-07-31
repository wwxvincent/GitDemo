package H100;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/23/24
 * @Description: 一个整数可以由连续的自然数之和来表示。给定一个整数，计算该整数有几种连续自然数之和的表达式，且打印出每种表达式。
 *
 * 输入：
 * 9
 * 输出：
 * 9=9
 * 9=4+5
 * 9=2+3+4
 * Result:3
 */
public class _06sequenceNumExpressionforSum {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.close();
        //System.out.println(num);

        solution(num);

    }

    // two point
    private static void solution(int num) {
        List<List<Integer>> list = new ArrayList<>();
        //list.add(List.of(num, num));
        //System.out.println(num+"="+num);
        int count = 0;

        //System.out.println("entery the solution");
        int left = 1 ;
        int right = left + 1;
        // small than, move right point
        // large than, move left point
        // stop at right point reach the last number
        int sum = left + right;
        while(right <= num && left <= right) {
            //System.out.println("sum:"+sum+" left:"+left+" right:"+right);
            if ( sum < num) {
                right++;
                sum += right;
            } else if ( sum > num) {
                sum -= left;
                left++;
            } else {
                list.add(List.of(left,right));
                count++;
                right++;
                sum+=right;
            }
        }

        print(num, list);
        System.out.println("Result:" +count);
    }
    private static void print(int target, List<List<Integer>> list) {
        System.out.println(target+"="+target);
        for(int i = list.size() -2; i >= 0; i--) {
            //System.out.println(list.get(i).get(0)+ "  " + list.get(i).get(1));
            System.out.print(target+"=");
            for(int j = list.get(i).get(0); j <= list.get(i).get(1) -1; j++) {
                System.out.print(j+"+");
            }
            System.out.println(list.get(i).get(1));
        }
    }
}
