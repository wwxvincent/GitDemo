package H100;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/25/24
 * @Description:
 */
public class _24transcript {
    private static String coursePick;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int studentNum = in.nextInt();
        int courseNum = in.nextInt();
        in.nextLine();
        String[] courseList = new String[courseNum];
        for(int i = 0; i < courseNum; i++) {
            courseList[i] = in.next();
        }
        in.nextLine();
        String[] record = new String[studentNum];
        for(int i = 0; i <studentNum; i++) {
            record[i] = in.nextLine();
        }

        coursePick = in.nextLine();

        in.close();

        solution();
    }

    private static void solution() {

    }
    static class Student implements Comparable<Student> {
        private String name;
        private Map<String, Integer> courses;
        int total = 0;

        public Student(String name) {
            this.name = name;
            this.courses = new HashMap<>();
        }
        public void addRecord(String course, int score) {
            courses.put(course, score);
            total += score;
        }



        @Override
        public int compareTo(Student o) {
            int p0 = total; int p1 = o.total;
            if (courses.containsKey(coursePick)) {
                p0 = courses.get(coursePick);
                p1 = o.courses.get(coursePick);
            }

            if (p0 != p1) return p1 - p0;
            return name.compareTo(o.name);
        }
    }

}
