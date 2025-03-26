import java.util.*;

public class TreeMapEx {
    public static void main(String[] args) {
        //21
        TreeMap<Integer, String> tm = new TreeMap<>();
        tm.put(1, "A");
        tm.put(2, "B");
        tm.put(3, "C");
        tm.put(4, "D");
        TreeMap<Integer, String> subTm = new TreeMap<>(tm.subMap(2, 4));
        System.out.println(subTm);

        //22
        TreeMap<Integer, String> subTm1 = new TreeMap<>(tm.subMap(3, true, 4, true));
        System.out.println(subTm1);

        //23
        System.out.println(tm.tailMap(2));

        //24
        System.out.println(tm.tailMap(2, false));

        //25
        System.out.println(tm.ceilingEntry(2));

        //26
        System.out.println(tm.ceilingKey(2));
    }
}
