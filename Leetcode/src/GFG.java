public class GFG {

    private static GFG instance;

    private GFG(){
        // private constructor
    }

    synchronized public static GFG getInstance(){
        if (instance == null) {
            instance = new GFG();
        }
        return instance;
    }
}
