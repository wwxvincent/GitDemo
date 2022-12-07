import java.util.Arrays;
import java.util.List;

public class exam {

    static private int voters = 0;
    int totalVoters;

    private static synchronized int Counter()
    {
        return ++voters;
    }

    public void getTotalVoterCount(){
        totalVoters = Counter();
    }

    public static void main (String[] args) {
        try {
            int c[] = {1};
            System.out.println(c.length);
            c[1] = 142;
            System.out.println("c = " + c[1]);
        } catch(ArithmeticException e) {
            System.out.println("Divide by 0: " + e);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println();
        }
    }
}
