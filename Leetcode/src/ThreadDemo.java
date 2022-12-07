public class ThreadDemo extends Thread{
    public void run(){
        System.out.println("before");
        this.stop();
        System.out.println("Afte");
    }

    public static void main(String args[]) {


    }
}
