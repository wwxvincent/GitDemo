package HCodeFronScatch;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/8/24
 * @Description:
 */
public class _08HappyNumber {
    private static Set<Integer> setList = new HashSet<>();

    public static void main (String[] args) {
        int n = 2;

        boolean res = newNum(n) == 1 ? true : false;
        System.out.println(res);

//        System.out.println(newNum(n));
    }



    private static int newNum(int n) {

        int res = 0;
        while (n > 0) {
            int cur = n % 10;
            n = n / 10;
            res += cur * cur;
        }
        if (res != 0 && !setList.contains(res)) {
            setList.add(res);
            return newNum(res);
        } else {
            return res;
        }
    }
}
