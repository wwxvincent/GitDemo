public class chatService implements Runnable{

    public void run(){
        System.out.print("Typing1 ");
        System.out.print("Typing2 ");
    }

    public static void main(String[] args) {
        try {
            Thread t = new Thread(new chatService());
            System.out.print("ChatRoom : ");
            t.start();
            System.out.print("chat ");
            t.join(0);
            System.out.print("Joined ");
        } catch (Exception e) {
            System.out.println("Exception Occurred");
        }
    }
}
