import java.util.*;

class Folder {
    int id;
    List<Folder> subfolders = new ArrayList<>();
    String name;
    public Folder(int id, List<Folder> input, String name) {
        this.id = id;
        for (Folder i : input ) {
            subfolders.add(i);
        }
        this.name = name;
    }
}
public class printPath {
    Map<Integer, String> map = new HashMap<>();


    public printPath(List<Folder> input){

        config(input);
    }

    private void config(List<Folder> input) {
        for (int i = 0; i < input.size(); i++) {

            Folder curr = input.get(i); // travesal input



            if(curr.id != 0 &&  !map.containsKey(curr.id)) continue;
            if (map.containsKey(curr.id)) {
                map.put(curr.id, map.get(curr.id) + "->" + curr.name);
            }

            List<Folder> subList = curr.subfolders; // find all the sub_folder
            // deal the sub-folders
            for(int j = 0; j < subList.size(); j++) {
                Folder temp = subList.get(j);
                if(  map == null || !map.containsKey(temp.id) ) {
                    map.put(temp.id, map.get(curr.id));
                }
//                } else {
//                    map.put(temp.id, map.get(temp.id)+ "->" + temp.name );
//                }

            }
        }
    }

    public void print(int id) {
        if (map.containsKey(id)) {
            System.out.println(map.get(id));
        } else {
            System.out.println();
        }
    }

    public static void main(String[] args){
        List<Folder> input = new ArrayList<>();
        List<Folder> temp = new ArrayList<>();
        Folder f9 = new Folder(9,temp,"lmn");
        Folder f8 = new Folder(8, temp, "def");
        temp.add(f9);
        Folder f7 = new Folder(7,temp , "ijk" );
        temp.clear();
        Folder f3 = new Folder(3, temp, "");
        temp.add(f3);
        temp.add(f7);
        Folder f0 = new Folder(0, temp, "abc");
        input.add(f0);
        input.add(f8);
        input.add(f7);
        input.add(f9);
        //System.out.println(input);

        printPath pp = new printPath(input);


        pp.print(9);

    }
}
