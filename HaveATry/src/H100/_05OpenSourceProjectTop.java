package H100;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/23/24
 * @Description:
 */

import java.util.*;

/**
*   输入描述：
 *
 * 第一行输入为N，表示开源项目的个数，0<N<=100。
 *
 * 第二行输入为权重值列表，一共5个整型值，分别对应关注、收藏、fork、issue、MR的权重，权重取值0<W<=50。
 *
 * 第三行开始接下来的N行为开源项目的统计维度
 *
 * 4
 * 8 6 2 8 6
 * camila 66 70 46 158 80
 * victoria 94 76 86 189 211
 * anthony 29 17 83 21 48
 * emily 53 97 1 19 218

 camila: 66*8 + 70*6 + 46*2 + 158*8 + 80*6 = 2784

 victoria: 94*8 + 76*6 + 86*2 + 189*8 + 211*6 = 4158

 anthony: 29*8 + 17*6 + 83*2 + 21*8 + 48*6 = 956

 emily: 53*8 + 97*6 + 1*2 + 19*8 + 218*6 = 2468

 输出：
 victoria
 camila
 emily
 anthony
*
 */
public class _05OpenSourceProjectTop {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);

        // read num of project
        int numOfProject = in.nextInt();

        int[] weightList = new int[5];
        for (int i = 0; i < 5; i++) {
            weightList[i] = in.nextInt();
        }

        // 直接存 名字 + score， 然后 collection排序
        Map<String, Integer> mapList1 = new HashMap<>();
        for( int i = 0; i < numOfProject; i++) {
            String name = in.next();
            int score = 0;
            for (int j = 0; j < 5; j++) {
                score += in.nextInt() * weightList[j];
            }
            mapList1.put(name, score);
        }

        in.close();
        // read done

        // collections sort
        List<Map.Entry<String, Integer>> nameList = new ArrayList<>(mapList1.entrySet());
//        Collections.sort(nameList, (a, b) ->
//                b.getValue().compareTo(a.getValue()));
        Collections.sort(nameList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                // compare value first;
                int valueComparison = o2.getValue().compareTo(o1.getValue());
                if (valueComparison != 0) {
                    return valueComparison;
                }
                // if value is same, compare the key
                return o1.getKey().compareTo(o2.getKey());
            }
        });


        for (Map.Entry<String, Integer> entry : nameList) {
            System.out.println(entry.getKey());
        }



//        Map<Integer, String> resMap = new TreeMap<>(Collections.reverseOrder());
//        for (Map.Entry<String, int[]> entry: mapList.entrySet()) {
//            String name = entry.getKey();
//            System.out.println(name);
//            int score = 0;
//            for(int i = 0; i < 5; i++) {
//                score += (entry.getValue())[i] * weightList[i];
//                //System.out.println("index: " + (entry.getValue())[i] + " weight: "+weightList[i]);
//            }
//            System.out.println("name: " + name + " score: "+score);
//            resMap.put(score, name);
//        }
//        System.out.println("map size: " + resMap.size());
        // 因为score可能有一样的，就不能用score当作key值存到新的一个map


    }

}
