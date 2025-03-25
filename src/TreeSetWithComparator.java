import java.util.TreeSet;
import java.util.Comparator;

// Класс Car не реализует Comparable
class Car {
    private String model;
    private int year;

    public Car(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public String getModel() { return model; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return model + " (" + year + ")";
    }
}

public class TreeSetWithComparator {
    public static void main(String[] args) {
        // Создаем Comparator для сортировки автомобилей по модели
        Comparator<Car> carComparator = new Comparator<Car>() {
            @Override
            public int compare(Car c1, Car c2) {
                return c1.getModel().compareTo(c2.getModel());
            }
        };

        // Передаем Comparator в конструктор TreeSet
        TreeSet<Car> cars = new TreeSet<>(carComparator);
        cars.add(new Car("Toyota", 2010));
        cars.add(new Car("Honda", 2008));
        cars.add(new Car("Ford", 2012));

        System.out.println("TreeSet с Comparator (сортировка по модели):");
        for (Car c : cars) {
            System.out.println(c);
        }
    }
}
