public class Salt implements Runnable {

    String Spice = "Yes";
    public void run() {
        this.Spice = "No";
    }

    public static void main(String[] args) throws InterruptedException {
        Salt t = new Salt();
        Thread spice_t = new Thread(t);
        spice_t.start();
        spice_t.sleep(1000);
        for(int i = 0; i <10; i++) {
            System.out.println(t.Spice);
        }
    }
}