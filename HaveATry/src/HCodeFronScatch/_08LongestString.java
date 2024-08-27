package HCodeFronScatch;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/8/24
 * @Description:
 */
public class _08LongestString {

    public static void main(String[] args) {

        String s =  "pwwkew";

        solution(s);

    }

    private static void solution(String str) {

        int maxLen = 0;
        for (int i = 0; i < str.length(); i++) {
            Set<Character> set = new HashSet<>();
            int index = i;
            while( index < str.length() && !set.contains(str.charAt(index))) {
                set.add(str.charAt(index));
                index++;
            }
            maxLen = Math.max(maxLen, set.size());
        }

        System.out.println(maxLen);
    }
}
