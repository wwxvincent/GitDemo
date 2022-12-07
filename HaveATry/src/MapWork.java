import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapWork {


    public static void main (String[] args){
        List<String> list = new ArrayList<>();

        list.add("w");
        list.add("x");
        list.add("vincent");
        list.add("wwx");

        Map<String, List<String>> map = new HashMap<>();
        //map.putAll(Map.ofEntries(Map.entry("POST", list)) );

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() +", value = " +entry.getValue());
        }
    }
}
