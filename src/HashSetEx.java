import java.util.*;

public class HashSetEx {
    public static void main(String[] args) {
        //7
        HashSet<String> hs = new HashSet<>();
        hs.add("1");
        hs.add("2");
        String[] newhs = new String[hs.size()];
        hs.toArray(newhs);
        System.out.println(hs.getClass());

        //8
        HashSet<String> hset = new HashSet<>();
        Set<String> treehset = new TreeSet<>(hset);
        System.out.println(treehset.getClass());

        //9
        HashSet<Integer> hsInt = new HashSet<>();
        hsInt.add(1);
        hsInt.add(7);
        for (Integer num : hsInt) {
            if (num < 7) {
                System.out.println(num + " is < than 7");
            }
        }

        //10
        HashSet<String> hs1 = new HashSet<>();
        hs1.add("a");
        hs1.add("b");
        hs1.add("d");

        HashSet<String> hs2 = new HashSet<>();
        hs2.add("a");
        hs2.add("c");
        hs2.add("d");

        for (String letter : hs1) {
            if (hs2.contains(letter)) {
                System.out.println("yes");
            }
            else {
                System.out.println("no");
            }
        }
        //11
        HashSet<String> hs3 = new HashSet<>();
        hs3.add("a");
        hs3.add("b");
        hs3.add("d");

        HashSet<String> hs4 = new HashSet<>();
        hs4.add("a");
        hs4.add("c");
        hs4.add("d");

        for (String letter : hs3) {
            if (hs4.contains(letter)) {
                System.out.println(letter);
            }
        }
        //12
        hs4.removeAll(hs4);
    }
}
