public class Calculator {

    public static void main(String[] args) {
        Integer[] a = {1,2,3};
        Object[] o = a;
        o[1] = "String  value";
        for(Object i : o) {
            System.out.println(i);
        }
    }
}
