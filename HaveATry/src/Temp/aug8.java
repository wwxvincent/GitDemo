package Temp;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/8/24
 * @Description:
 */
public class aug8 {


    public static void main(String[] args) {

        System.out.println(solution(5, 2, 2, 3) );
//        System.out.println(solution(3, 1, 2, 3) );

    }

    private static int solution(int xCountry, int yCounty, int x, int y) {
        int numX = 0; // 只能去X
        int numY = 0; // only Y
        int numA = 0; // Any
        int index = 1;

        // 所需总人数
        int target = xCountry + yCounty;

        while ( (numX + numY + numA) < target ) {
            if ( !helper(index, x) && !helper(index, y)) {
                numA++;
            } else if ( helper(index, x) && !helper(index, y)) {
                numY++;
            } else if ( helper(index, y) && !helper(index, x)) {
                numX++;
            }
            index++;
        }
        return index;
    }

    private static boolean  helper(int num, int multiple) {
        if (num < multiple) return false;
        if (num % multiple == 0) {
            return true;
        } else {
            return false;
        }

    }
}

// numX : store people for X
