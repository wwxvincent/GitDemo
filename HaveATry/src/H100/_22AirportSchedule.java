package H100;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/25/24
 * @Description:
 */
public class _22AirportSchedule {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        in.close();
        String[] strArr = line.split(",");

        Arrays.sort(strArr);

        System.out.println(String.join(",", strArr));
    }
}
