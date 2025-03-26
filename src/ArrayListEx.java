import java.util.*;

public class ArrayListEx {
    public static void main(String[] args) {
        // 17
        ArrayList<String> arr = new ArrayList<>();
        arr.add("red");
        arr.add("blue");
        arr.removeAll(arr);

        //18
        System.out.println(arr.isEmpty());

        //19
        arr.add("red");
        arr.add("blue");
        arr.trimToSize();

        //20
        arr.ensureCapacity(4);
        arr.add("yellow");

        //21
        arr.set(1, "White");

        //22
        for (int i=0; i<arr.size(); i++) {
            System.out.println(arr.get(i));
        }
    }
}

