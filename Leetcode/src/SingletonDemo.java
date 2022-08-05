import java.util.logging.Logger;

public class SingletonDemo {
    private static SingletonDemo singletonDemo = new SingletonDemo();
    private static Logger logger = Logger.getLogger("SingletonDemo");

    private SingletonDemo(){

    }

    public static SingletonDemo getInstance(){
        System.out.println("from getinstance");
        logger.info("from getInstance");
        return singletonDemo;
    }

    public void getTranslation() {
        System.out.println("here1");
        logger.info("from other method");
    }
}
