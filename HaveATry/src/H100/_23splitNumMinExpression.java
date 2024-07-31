package H100;

import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/25/24
 * @Description:
 */
public class _23splitNumMinExpression {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.close();

        solution(num);
    }

    private static void solution(int num) {
        if(num % 2 != 0) {
            System.out.println(num+"="+ num/2+ "+" + (num/2 +1));

        } else if (num % 3 == 0) {
            System.out.println(num+"="+ ( (num/3) - 1 ) + "+"+ ( (num/3) + 1 ));
        } else {

        }
    }
}
