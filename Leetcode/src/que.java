import java.util.Scanner;
import java.util.stream.Stream;

public class que {





    public  static void main (String[] args){
        Integer[] a = { 1, 2, 3};

        Stream<Integer> s = Stream.of(a);

        //s.forEach(p -> System.out.println(p));
        // find all the number which can be divide by 3
        Stream<Integer> stream = Stream.of(new Integer[]{1,2,3,4,5,6,7,8,9,10});

        Integer[] res = stream.filter( p -> p % 3 == 0).filter(i -> i % 2== 0).toArray(Integer[] :: new);
        for(Integer i : res) {
            System.out.println(i);
        }
    }
}
