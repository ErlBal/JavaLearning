import java.util.*;

public class HashMapEx {
    public static void main(String[] args) {
        //7
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("a", 1);
        System.out.println(hm.containsKey("a"));
        System.out.println(hm.containsKey("b"));

        //8
        System.out.println(hm.containsValue(1));
        System.out.println(hm.containsValue(2));

        //9
        HashMap<String, Integer> hm2 = new HashMap<>();
        hm2.put("a", 1);
        hm2.put("b", 2);
        Set<Map.Entry<String, Integer>> set = hm2.entrySet();
        System.out.println(set);

        //10
        hm.get("a");

        //11
        System.out.println(hm.keySet());

        //12
        System.out.println(hm2.values());
    }
}
