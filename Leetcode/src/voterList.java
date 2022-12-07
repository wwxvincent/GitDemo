public class voterList {

    static private int voters = 0 ;
    int totalVoters;

    private static synchronized int Counter() {
        return ++voters;
    }

    public void getTotalCount(){
        totalVoters = Counter();
    }

    public static void main(String[] args) {
        voterList v = new voterList();
    }
}
