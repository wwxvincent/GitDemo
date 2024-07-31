package H100;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/24/24
 * @Description:
 * 示例1
 * 输入：
 * 4
 * 100 200 300 500
 * 1 2
 * 1 3
 * 2 4
 * 输出：700
 *
 * 说明：
 * 成员1,2,3组成的小家庭财富值为600
 * 成员2,4组成的小家庭财富值为700
 */
public class _12FindRichestFamily {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);

        int num = in.nextInt();
        if (num < 1 || num > 1000) throw new RuntimeException("family member number error");
        int[] balanceArr = new int[num];
//        System.out.println(balanceArr.length);
        for( int i = 0; i < num; i++) {
            balanceArr[i] = in.nextInt();
//            System.out.println(balanceArr[i]);
            if (balanceArr[i] <0 || balanceArr[i] > 1000000) throw new RuntimeException("balance error");
        }
        in.nextLine();
        Map<String, Integer> familyMap = new HashMap<>();
        int maxBalance = 0;
        //String temp = in.nextLine();
       // System.out.println(temp);
//        System.out.println(num);
        for(int i = 0; i < num-1; i++) {
            String line = in.nextLine();
//            System.out.println(line);
            String[] relationArr = line.split(" ");
//            System.out.println("length of arr "+relationArr.length);
            String family = relationArr[0];
            String son = relationArr[1];
//            System.out.println("family: "+family+" son "+son);
            familyMap.put( family, familyMap.getOrDefault(family,balanceArr[Integer.parseInt(family)-1]) + balanceArr[Integer.parseInt(son)-1]);
            maxBalance = Math.max(maxBalance, familyMap.get(family));
        }
        in.close();

        System.out.println(maxBalance);

    }

}
