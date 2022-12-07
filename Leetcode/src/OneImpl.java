public class OneImpl extends OneSuper{

    public OneImpl(int key) {
        super(key);

    }

    @Override
    void firstAction(){

    }
}

abstract class OneSuper {
    int key;
    public OneSuper(int key) {
        this.key = key;
    }

    abstract void firstAction();

    public void secondAction(){
        System.out.println("Action");
    }
}
