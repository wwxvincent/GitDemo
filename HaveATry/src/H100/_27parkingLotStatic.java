package H100;

import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/27/24
 * @Description:
 *
 * 特定大小的停车场，数组cars[]表示，其中1表示有车，0表示没车。车辆大小不一
 * ，小车占一个车位（长度1），货车占两个车位（长度2），卡车占三个车位（长度3），
 * 统计停车场  --- 最少  ---可以停多少辆车，返回具体的数目。
 *
 * [ 1, 0, 1, 1, 1, 0 , 1, 1 ]
 */
public class _27parkingLotStatic {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        in.close();
        String[] arr = line.split(",");
        solution(arr);
    }

    private static void solution(String[] arr) {

        int res = 0;
        int len = 0;
        int flag = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.println("arr["+i+"]:"+arr[i]);
            if (arr[i].equals("1")) {
                if (flag == 1) {
                    len++;
                } else {
                    len = 1;
                }
                flag = 1;
            } else {
                flag = 0;
                res += countCarLength(len);
                len = 0;
            }
        }
        res += countCarLength(len);
        System.out.println(res);
    }
    private static int countCarLength(int len) {
        int res = 0;
        res += len / 3;
        res += (len % 3) /2 ;
        res += (len % 3) % 2;
        return res;
    }

}
