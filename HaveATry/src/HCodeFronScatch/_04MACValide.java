package HCodeFronScatch;

import java.util.*;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/5/24
 * @Description:
 */
public class _04MACValide {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        in.close();

        Character[] dic = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','A','B','C','D','E','F'};
//        String dic  = "0123456789abcdefABCDEF";
        List<Character> dicList = Arrays.asList(dic);

//        solution(line, dic);
        solution2(line, dicList);
    }

    private static void solution(String line, List<Character> dic) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            if(!dic.contains(line.charAt(i)+"")) {
                i++;
                continue;
            }
            char[] temp = new char[12];
            int len = 1;
            temp[len-1] = line.charAt(i);

            int right = i + 1;
            while (right < line.length()) {
                if(len == 12) {
                    String resTemp = Arrays.toString(temp);
                    if(!res.contains(resTemp)) {
                        res.add(resTemp);
                    }
                    break;
                }
                if (dic.contains(line.charAt(right)+"")) {
                    len++;
                    temp[len-1] = line.charAt(right);
                }
                right++;
            }
        }

        System.out.println(res.size());
        for(String cur : res) {
            System.out.println(cur);
        }
    }

    private static void solution2(String line, List<Character> dic) {
        List<String> res = new ArrayList<>();
        int left = 0;
        // find first valid number
        while(!dic.contains(line.charAt(left))) {
            left++;
        }
        int len = 1;
        Queue<Character> queue = new LinkedList<>();
        queue.add(line.charAt(left));
        int right = left + 1;
        while (right < line.length()) {
            char flag= line.charAt(right);
            if (len == 12) {
                if(!res.contains(queue.toString())) {
                    res.add(queue.toString());
                }
                queue.remove();
                len--;
//                left++;
                // right 进了一位，还没有进行判断，得把它用掉
                if (dic.contains(flag)) {
                    queue.add(flag);
                    len++;
                }
            }else if (dic.contains(flag)) {
                queue.add(line.charAt(right));
                len++;
            }
            right++;

        }

        System.out.println(res.size());
        for(String cur : res) {
            System.out.println(cur);
        }
    }
}
