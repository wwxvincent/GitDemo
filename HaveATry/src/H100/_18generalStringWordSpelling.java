package H100;

import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/25/24
 * @Description:
 */
public class _18generalStringWordSpelling {

    public static void main (String[] args) {

        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        String[] strArr = new String[num];
        for ( int i = 0; i < num; i++ ) {
            strArr[i] = in.nextLine();
        }
        String library = in.nextLine();
        in.close();


        solution(library, strArr);
    }

    private static int[] libraryGenerator(String library) {
        int[] libraryArr = new int[27];
        for(int i =0; i < library.length(); i++) {
            int value =  library.charAt(i) - 'a';
            if(value == -34) libraryArr[26]++;
            if (value >= 0 && value <26) libraryArr[value]++;
        }
        return libraryArr;
    }
    private static int check(int[] libraryArr, String word) {
        int res = 1;
        for(int i = 0; i < word.length(); i++) {
            int location = word.charAt(i) - 'a';
            if(location <0 || location > 25) {System.out.println(i+" word input error: " + word); }
            libraryArr[location] = libraryArr[location]-1;
            if (libraryArr[location] < 0) return 0;
        }
        return res;
    }
    private static void solution(String library, String[] strArr) {
        int[] libraryArr = libraryGenerator(library);
        int res = 0;

        for (String word: strArr) {
            res += check(libraryArr, word);
        }
        System.out.println("\n"+res);
    }
}
