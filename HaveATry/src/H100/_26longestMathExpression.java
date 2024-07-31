package H100;

import java.util.Scanner;
import java.util.Stack;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/26/24
 * @Description:
 *
 * 提取字符串中的最长合法简单数学表达式，字符串长度最长的，并计算表达式的值。如果没有，则返回0
 * 简单数学表达式只能包含以下内容
 * 0-9数字，符号 +-*
 *
 */
public class _26longestMathExpression {
    private static char[] operator = {'+', '-', '*'};

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        in.close();

        solution(line);
    }

    private static void solution (String line) {

        if (line.isEmpty()) return ;

        Stack<Long> values = new Stack<>(); // use stack to store result dynastic

        long currNum = 0;
        char operator = '+';

        for (int i = 0; i < line.length(); i++) {
            char curr = line.charAt(i);
        }

    }
}
