public class ImageEditor implements crop, reSize{




    @Override
    public void cropImage() {

    }

    @Override
    public void resizeImage() {

    }

    public static void main(String[] args) {
        //System.out.print(defDimension);
    }
}

interface crop{
    String defDimension = "1216";
    void cropImage();
    void resizeImage();
}

interface reSize{
    String defDimension = "1024";
    void resizeImage();
}