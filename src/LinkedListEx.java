import java.util.*;

public class LinkedListEx {
    public static void main(String[] args) {
        //21
        LinkedList<String> ll = new LinkedList<>();
        ll.offer("1");
        ll.offer("2");
        ll.offer("4");
        System.out.println(ll.peek());

        //22
        System.out.println(ll.contains("1"));

        //23
        LinkedList<String> ll1 = new LinkedList<>();
        List<String> arrll = new ArrayList<>(ll1);
        System.out.println(arrll.getClass());

        //24
        LinkedList<String> newll = new LinkedList<>();
        newll.add("1");
        newll.add("2");
        newll.add("3");

        LinkedList<String> ll3 = new LinkedList<>();
        for (String str : ll) {
            ll3.add(newll.contains(str) ? "yes" : "no");
        }
        System.out.println(ll3);

        //25
        System.out.println(ll.isEmpty());

        //26
        ll.set(1, "5");
    }
}
