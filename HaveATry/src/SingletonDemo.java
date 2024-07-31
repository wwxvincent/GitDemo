/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 6/25/24
 * @Description:
 */
public class SingletonDemo {
    // 私有静态变量，保存类的唯一实例
    private static SingletonDemo instance;
    private int count=0;

    // 私有构造方法，防止外部通过new创建实例
    private SingletonDemo() {
        count++;
    }

    // 公有静态方法，提供全局访问点
    public static SingletonDemo getInstance() {
        //双重检查锁定模式
//        if(instance == null) {
//            synchronized (SingletonDemo.class) {
//                if (instance == null) {
//                    instance = new SingletonDemo();
//                }
//            }
//        }
        instance = new SingletonDemo();
        return instance;
    }

    // 其他业务方法
    public void doSomething() {
        System.out.println("DO something: " + count);
    }

    public static void main (String[] args) {
        SingletonDemo singletonDemo = SingletonDemo.getInstance();
        singletonDemo.doSomething();
        SingletonDemo singletonDemo2 = SingletonDemo.getInstance();
        singletonDemo2.doSomething();

    }
}
