package H100;

import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/23/24
 * @Description:
 */
public class _11MaxLocation {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int bonus = in.nextInt();
        int locationMax = 0;
        int location = 0;
        //int[] instruction = new int[num];
        for (int i = 0; i < num-1; i++) {
            int go = in.nextInt();
            if(go == bonus) {
                go = go < 0 ? go-1 : go+1;
            }
            location += go;
            locationMax = Math.max(location, locationMax);
        }

        in.close();

        System.out.println("\n"+locationMax);
    }
}
