package H100;

import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/23/24
 * @Description:
 * 输入描述：
 * 第一行为N，表示访问历史日志的条数，0<N<=100
 * 接下来N行，每一行为一个RESTful API的URL地址，约束地址中仅包含英文字母和连接符/，最大层级为10，每层级字符串最大长度为10。
 * 最后一行为层级L和要查询的关键字。
 * 输出描述：
 * 输出给定层级上，关键字出现的频次，使用完全匹配方式（大小写敏感）。
 *
 *  input
 * 5
 * /huawei/computing/no/one
 * /huawei/computing
 * /huawei
 * /huawei/cloud/no/one
 * /huawei/wireless/no/one
 * 2 computing
 *
 * output
 * 2
 */
public class _07APICluster {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = Integer.parseInt(in.nextLine());
        String[] strArr = new String[num];
        for(int i = 0; i < num; i++) {
            strArr[i] = in.nextLine();
        }
        int level = in.nextInt();
        String target = in.next();
        in.close();

        solution(target, level, strArr);
    }

    private static void solution(String target, int level, String[] strArr) {
        int count = 0;

        for(String str : strArr) {
            String[] checkList = str.split("/");
            //System.out.println(checkList.length);
            //System.out.println(level);
            if (checkList.length > level && checkList[level].equals(target)) {
                //System.out.println(checkList[level]);
                count++;
            }

        }

        System.out.println(count);
    }
}
