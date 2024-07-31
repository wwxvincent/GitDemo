package H100;

import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/22/24
 * @Description:
 */
//  S中的每个字符在L中都能找到（可以不连续），
//  且S在Ｌ中字符的前后顺序与S中顺序要保持一致。
// （例如，S="ace"是L="abcde"的一个子序列且有效字符是a、c、e，
//  而"aec"不是有效子序列，且有效字符只有a、e）
//
//
//

public class _01StringOrderDetermine {

//    public int solution(String)

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String S = in.next();
        String L = in.next();
        in.close();

        System.out.println(solution(S,L));
    }

    private static int solution(String S, String L) {
        if(S.length() > L.length()) return 0;
        int indexS = 0;
        int indexL = 0;

        while( indexS < S.length() && indexL < L.length()) {
            char charS = S.charAt(indexS);
            char charL = L.charAt(indexL);
            if(charS == charL) {
                indexS++;
                indexL++;
            } else {
                indexL++;
            }
        }
        if (indexS == S.length()) return indexL-1;
        return -1;
    }


}
