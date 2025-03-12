import java.util.*;

class ListMachinations {
    // 1:
    public static <T> List<T> mergeLists(List<T> list1, List<T> list2) {
        List<T> result = new ArrayList<>();
        int i = 0;

        while (i < list1.size() || i < list2.size()) {
            if (i < list1.size()) {
                result.add(list1.get(i));
            }
            if (i < list2.size()) {
                result.add(list2.get(i));
            }
            i++;
        }

        return result;
    }

    // 2:
    public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }
    }
}

// 3:
class Task implements Comparable<Task> {
    private String taskName;
    private int priority;
    private int duration;

    public Task(String taskName, int priority, int duration) {
        this.taskName = taskName;
        this.priority = priority;
        this.duration = duration;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getPriority() {
        return priority;
    }

    public int getDuration() {
        return duration;
    }

    public String toString() {
        return "[priority " + priority + "] " + taskName + " (duration: " + duration + " min)";
    }

    public int compareTo(Task other) {
        int priorityCompare = Integer.compare(other.priority, this.priority);

        if (priorityCompare == 0) {
            return Integer.compare(this.duration, other.duration);
        }

        return priorityCompare;
    }
}

class TaskScheduler {
    private PriorityQueue<Task> scheduledTasks;
    private Queue<Task> pendingTasks;

    public TaskScheduler() {
        scheduledTasks = new PriorityQueue<>();
        pendingTasks = new LinkedList<>();
    }

    public void addTask(Task task) {
        scheduledTasks.add(task);
        System.out.println("[priority " + task.getPriority() + "] " + task.getTaskName() + " (duration: " + task.getDuration() + " min)");
    }

    public Task nextTask() {
        if (!scheduledTasks.isEmpty()) {
            Task task = scheduledTasks.poll();
            System.out.println("processing task: " + task);
            return task;
        } else if (!pendingTasks.isEmpty()) {
            Task task = pendingTasks.poll();
            System.out.println("processing task: " + task);
            return task;
        }

        System.out.println("no tasks to process.");
        return null;
    }

    public void delayTask(String taskName) {
        Iterator<Task> iterator = scheduledTasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getTaskName().equals(taskName)) {
                iterator.remove();
                pendingTasks.add(task);
                System.out.println("delaying task: " + taskName);
                return;
            }
        }
        System.out.println("task not found: " + taskName);
    }

    public void printScheduledTasks() {
        if (scheduledTasks.isEmpty()) {
            System.out.println("scheduled tasks sorted by priority:");
            System.out.println("no priority tasks");
            return;
        }

        System.out.println("scheduled tasks sorted by priority:");
        PriorityQueue<Task> tempQueue = new PriorityQueue<>(scheduledTasks);
        int count = 1;

        while (!tempQueue.isEmpty()) {
            System.out.println(count + ". " + tempQueue.poll());
            count++;
        }
    }

    public void printPendingTasks() {
        if (pendingTasks.isEmpty()) {
            System.out.println("pending Tasks (FIFO order):");
            System.out.println("(no pending tasks)");
            return;
        }

        System.out.println("pending tasks (FIFO order):");
        int count = 1;

        for (Task task : pendingTasks) {
            System.out.println(count + ". " + task.getTaskName() + " (priority: " +
                    task.getPriority() + ", duration: " + task.getDuration() + " min)");
            count++;
        }
    }
}

public class TaskSched {
    public static void main(String[] args) {
        // 1:
        List<String> list1 = Arrays.asList("cho", "priv", "halo");
        List<String> list2 = Arrays.asList("47", "32", "0");
        List<String> mergedList = ListMachinations.mergeLists(list1, list2);
        System.out.println("merged list: " + mergedList);

        // 2:
        Map<String, Integer> map = new HashMap<>();
        map.put("odin", 1);
        map.put("dva", 2);
        map.put("tri", 3);
        System.out.println("\nmap entries:");
        ListMachinations.printMap(map);

        // 3:
        TaskScheduler scheduler = new TaskScheduler();

        System.out.println("tasks added:");
        scheduler.addTask(new Task("task1", 3, 20));
        scheduler.addTask(new Task("taskOne", 5, 45));
        scheduler.addTask(new Task("task", 2, 30));
        scheduler.addTask(new Task("taskOdin", 5, 50));
        scheduler.addTask(new Task("tasks", 4, 25));
        System.out.println();

        scheduler.printScheduledTasks();
        System.out.println();

        scheduler.nextTask();
        System.out.println();

        scheduler.delayTask("task1");
        scheduler.printScheduledTasks();
        scheduler.printPendingTasks();
        System.out.println();

        scheduler.delayTask("task");
        scheduler.printScheduledTasks();
        scheduler.printPendingTasks();
        System.out.println();

        scheduler.nextTask();
        System.out.println();

        scheduler.printScheduledTasks();
        scheduler.printPendingTasks();
        System.out.println();

        scheduler.nextTask();
        System.out.println();

        scheduler.printScheduledTasks();
        scheduler.printPendingTasks();
        System.out.println();

        scheduler.nextTask();
        System.out.println();

        scheduler.printScheduledTasks();
        scheduler.printPendingTasks();
        System.out.println();

        scheduler.nextTask();
        System.out.println();

        scheduler.printScheduledTasks();
        scheduler.printPendingTasks();
    }
}
