package Temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 11/12/24
 * @Description:
 */
public class uuid {

    public static void main (String[] args) {
        List<Integer> intArr = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        String str = intArr.toString().replace("[", "").replace("]", "").replace(" ","");
        String[] arr = str.split(",");
        for (String s : arr) {
            System.out.println(s);
        }
        System.out.println(str);
    }
}
