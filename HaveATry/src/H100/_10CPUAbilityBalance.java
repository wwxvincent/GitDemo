package H100;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/23/24
 * @Description:
 */
public class _10CPUAbilityBalance {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int numA = in.nextInt();
        int numB = in.nextInt();

        int[] arrA = new int[numA];
        int sumA = 0;
        for (int i = 0; i < numA; i++) {
            arrA[i] = in.nextInt();
            sumA += arrA[i];
        }

        int[] arrB = new int[numB];
        int sumB = 0;
        for (int i = 0; i < numB; i++) {
            arrB[i] = in.nextInt();
            sumB += arrB[i];
        }

        in.close();


        solution(arrA, arrB, (sumA-sumB)/2);


    }
    private static void solution(int[] arrA, int[] arrB, int diff) {
        Arrays.sort(arrA);
        Arrays.sort(arrB);
        int pointA = 0;
        int pointB = 0;

        while (arrA[pointA] - arrB[pointB] != diff && ( pointA < arrA.length || pointB < arrB.length) ) {
            if (arrA[pointA] - arrB[pointB] < diff) {
                pointA++;
            } else {
                pointB++;
            }
        }

        System.out.println(arrA[pointA] + " " + arrB[pointB]);
    }
}
