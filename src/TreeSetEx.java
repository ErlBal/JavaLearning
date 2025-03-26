import java.util.*;

public class TreeSetEx {
    public static void main(String[] args) {
        //11
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(1);
        ts.add(5);
        ts.add(6);
        ts.add(7);
        System.out.println(ts.floor(8));

        //12
        System.out.println(ts.ceiling(5));

        //13
        System.out.println(ts.lower(4));

        //14
        System.out.println(ts.pollFirst());

        //15
        System.out.println(ts.pollLast());

        //16
        ts.remove(5);
    }
}
