package H100;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/24/24
 * @Description:
 *
 * 说明：
 * 在虚拟游戏中，每项投资风险值相加为总风险值；
 * 在虚拟游戏中，最多只能投资2个理财产品；
 * 在虚拟游戏中，最小单位为整数，不能拆分为小数；
 * 投资额*回报率=投资回报
 * 输入描述：
 * 第一行：产品数(取值范围[1, 20])，总投资额(整数，取值范围[1, 10000])，可接受的总风险(整数，取值范围[1, 200])
 * 第二行：产品投资回报率序列，输入为整数，取值范围[1,60]
 * 第三行：产品风险值序列，输入为整数，取值范围[1,100]
 * 第四行：最大投资额度序列，输入为整数，取值范围[1,10000]
 * 输出描述：
 * 每个产品的投资额序列
 * 补充说明：
 * 在虚拟游戏中，每项投资风险值相加为总风险值；
 * 在虚拟游戏中，最多只能投资2个理财产品；
 * 在虚拟游戏中，最小单位为整数，不能拆分为小数；
 * 投资额*回报率=投资回报
 */
public class _15virtualGameMoney {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int productNum = in.nextInt();
        int investmentLimit = in.nextInt();
        int riskMax = in.nextInt();
        in.nextLine();
        int[] returnRate = new int[productNum];
        for( int i = 0; i < productNum; i++) {
            returnRate[i] = in.nextInt();
        }
        in.nextLine();
        int[] risk = new int[productNum];
        for ( int i = 0; i < productNum; i++) {
            risk[i] = in.nextInt();
        }
        in.nextLine();
        int[] investmentMax = new int[productNum];
        for ( int i = 0; i < productNum; i++) {
            investmentMax[i] = in.nextInt();
        }

        solution(returnRate, risk, investmentMax, investmentLimit, riskMax, productNum);
    }

    private static void solution(int[] returnRate, int[] risk, int[] investmentMax, int investmentLimit, int riskMax, int num) {

        int maxReturn = 0;
        int[] res = new int[num];
        for (int i = 0; i < num; i++) {
            for (int j = i +1; j < num; j++) {
                if (risk[i] + risk[j] <= riskMax && investmentMax[i] + investmentMax[j] <= investmentLimit) {
                    int curReturn = returnRate[i]*investmentMax[i] + returnRate[j]*investmentMax[j];
                    if (curReturn > maxReturn) {
                        int[] temp = new int[num];
                        temp[i] = investmentMax[i];
                        temp[j] = investmentMax[j];
                        res = Arrays.copyOf(temp, num);
                    }
                    maxReturn = Math.max( maxReturn, curReturn);
                }
            }
            if (risk[i] <= riskMax && investmentMax[i]  <= investmentLimit && returnRate[i] * investmentMax[i] > maxReturn) {
                int[] temp = new int[num];
                temp[i] = investmentMax[i];
                res = Arrays.copyOf(temp, num);
                maxReturn = Math.max(maxReturn, returnRate[i] * investmentMax[i]);
            }
        }

        for(int a : res) {
            System.out.print( a +" ");
        }
    }
}
