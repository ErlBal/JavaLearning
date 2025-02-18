interface Payable {
    double getPaymentAmount();
}

abstract class Employee implements Payable {
    String name;
    int id;
    double baseSalary;
    boolean healthInsurance;

    public Employee(String name, int id, double baseSalary, boolean healthInsurance) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
        this.healthInsurance = healthInsurance;
    }

    public abstract double calculateSalary();

    public void displayEmployeeInfo() {
        System.out.println("id: " + id + ", name: " + name + ", base salary: $" + baseSalary + ", total salary: $" + calculateSalary() + ", health insurance: " + healthInsurance);
        System.out.println("--------------------------");
    }
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name, int id, double baseSalary) {
        super(name, id, baseSalary, true);
    }

    public double calculateSalary() {
        return baseSalary * 1.2;
    }

    public double getPaymentAmount() {
        return calculateSalary();
    }
}

class ContractEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public ContractEmployee(String name, int id, double hourlyRate, int hoursWorked) {
        super(name, id, 0, false);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }

    public double getPaymentAmount() {
        return calculateSalary();
    }
}

public class EmpManagSys {
    public static void main(String[] args) {
        Employee[] employees = new Employee[2];
        employees[0] = new FullTimeEmployee("unemployed", 1, 1984);
        employees[1] = new ContractEmployee("employer", 2, 12, 8);

        for (Employee e: employees) {
            e.displayEmployeeInfo();
            e.calculateSalary();
        }
    }
}

