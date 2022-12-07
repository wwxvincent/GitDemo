import java.util.Arrays;
import java.util.Random;

public class ArrayOrdering {

    final static int ARRSIZE = 200;

    public static void main(String[] args) {


        int[] arr = new int[ARRSIZE];
        Random r = new Random();
        for (int count = 0; count < ARRSIZE; count++){
            arr[count] = r.nextInt(100 - 1 + 1) + 1;
        }
        Arrays.parallelSort(arr);
    }



}
