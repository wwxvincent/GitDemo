public class Order {

    String OrderId;
    Order(){
        OrderId = "000A";
        System.out.println(OrderId);
    }
    Order(String od) {
        OrderId = od;
        System.out.println(OrderId);
    }
}

class RaiseTicket extends Order{
    String OrderId;
    RaiseTicket() {
        OrderId = "xxxB";
        System.out.println(OrderId);
    }
    RaiseTicket(String od) {
        OrderId = od;
        System.out.println(OrderId);
    }

    public static void main(String[] args) {
        RaiseTicket b = new RaiseTicket("1908");
    }
}
