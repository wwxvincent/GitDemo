package H200;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/31/24
 * @Description:
 */
public class _05RockPapperScissor {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);

        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new ArrayList<>());
        }

        String name;
        char op;
        while (in.hasNext()) {
            name = in.next();
            op = in.next().charAt(0);
            list.get(op - 'A').add(name);
        }
        // check how many different shape
        int num = 0;
        for (int i = 0; i < 3; i++) {
            if( list.get(i).size() > 0) {
                num++;
            }
        }

    }
}
