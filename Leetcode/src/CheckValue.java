public class CheckValue {
    int val;
    CheckValue(int v) {
        val = v;
    }
    void compare(CheckValue cv) {
        System.out.println("" + (cv.val == this.val));
    }

    public static void main (String[] args) {
        CheckValue cv1 = new CheckValue(10);
        CheckValue cv2 = new CheckValue(10);
        System.out.println(cv1 == cv2);
        cv2.compare(cv1);
    }
}
