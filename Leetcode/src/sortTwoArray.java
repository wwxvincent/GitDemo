//
//
// sort and merge two unsorted arrays : inplace

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

public class sortTwoArray {

    public void sort(int[] arr1, int[] arr2) {


        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int index1 = 0;
        int index2 = 0;

        while (index1 < arr1.length || index2 <arr2.length) {
            if (index1 >= arr1.length) {
                System.out.println(arr2[index2]);
                index2++;
            } else if (index2 >= arr2.length) {
                System.out.println(arr1[index1]);
                index1++;
            }else if (arr1[index1] <= arr2[index2] ){
                System.out.println(arr1[index1]);
                index1++;
            } else  {
                System.out.println(arr2[index2]);
                index2++;
            }
        }


    }

    public int[] merge(int[] arr1, int[] arr2){
        int index1=0;
        int index2=0;
        int index = 0;
        int[] res = new int[arr1.length+arr2.length];
        while (index1 < arr1.length && index2 < arr2.length) {
            if (arr1[index1] < arr2[index2]) {
                res[index] = arr1[index1];
                index++;
                index1++;
            } else {
                res[index] = arr2[index2];
                index++;
                index2++;
            }
        }
        System.out.println(index2);
        while (index1 < arr1.length){
            System.out.println("from 1");
            res[index] = arr1[index1];
            index++;
            index1++;
        }
        while (index2 < arr2.length){
            res[index] = arr2[index2];
            index++;
            index2++;
        }
        return res;
    }

    public static void main(String[] args){
        int[] arr1 = {1,2,3,4,4,5};
        int[] arr2 = {2,3,5,6,6,7};
        sortTwoArray s = new sortTwoArray();
        int[] temp = s.merge(arr1, arr2);

        for (int i : temp) {
            System.out.println(i);
        }
    }

}
