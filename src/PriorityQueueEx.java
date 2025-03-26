import java.util.*;

public class PriorityQueueEx {
    public static void main(String[] args) {
        //7
        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.add("A");
        pq.add("B");
        pq.add("C");
        PriorityQueue<String> pq2 = new PriorityQueue<>();
        pq2.add("A");
        pq2.add("B");
        pq2.add("D");
        for (String s : pq) {
            System.out.println(pq2.contains(s) ? "Yes" : "No");
        }

        //8
        System.out.println(pq.peek());

        //9
        pq.poll();

        //10
        String[] arr = pq.toArray(new String[0]);
        System.out.println(Arrays.toString(arr));

        //11
        PriorityQueue<String> pq3 = new PriorityQueue<>();
        pq3.add("A");
        pq3.add("B");
        pq3.add("C");
        String str = pq3.toString();

        //12
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        pq1.add(1);
        pq1.add(2);
        pq1.add(3);
        pq1.add(4);
        pq1.add(5);
        pq1.add(6);
        pq1.add(7);
        System.out.println(pq1);
        while(!pq1.isEmpty()) {
            System.out.print(pq1.poll());
        }
    }
}

