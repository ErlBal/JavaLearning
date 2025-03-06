// Интерфейс, определяющий операции с банковским счетом
interface AccountOperation {
    void deposit(double amount);
    void withdraw(double amount) throws InsufficientFundsException;
    double getBalance();
}

// Пользовательское исключение (самодельное)
class InsufficientFundsException extends Exception {
    private double requestedAmount;
    private double availableBalance;

    public InsufficientFundsException(double requestedAmount, double availableBalance) {
        super("Недостаточно средств на счете: запрошено " + requestedAmount +
                ", доступно " + availableBalance);
        this.requestedAmount = requestedAmount;
        this.availableBalance = availableBalance;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }
}

// Абстрактный класс для банковского счета
abstract class BankAccount implements AccountOperation {
    private String accountNumber;
    protected double balance;  // protected, чтобы подклассы имели прямой доступ

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Реализация методов из интерфейса
    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма депозита должна быть положительной");
        }
        balance += amount;
        System.out.println("Внесено: " + amount + " на счет " + accountNumber);
    }

    @Override
    public double getBalance() {
        return balance;
    }

    // Абстрактный метод, который должны реализовать все подклассы
    public abstract String getAccountType();

    public String getAccountNumber() {
        return accountNumber;
    }

    // Метод с обычным исключением (IllegalStateException)
    public void closeAccount() {
        if (balance > 0) {
            throw new IllegalStateException("Невозможно закрыть счет с положительным балансом: " + balance);
        }
        System.out.println("Счет " + accountNumber + " успешно закрыт.");
    }
}

// Подкласс для сберегательного счета
class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, double initialBalance, double interestRate) {
        super(accountNumber, initialBalance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        // Проверка достаточности средств
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }

        balance -= amount;
        System.out.println("Снято: " + amount + " со счета " + getAccountNumber());
    }

    public void addInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("Начислены проценты: " + interest + " на счет " + getAccountNumber());
    }

    @Override
    public String getAccountType() {
        return "Сберегательный счет";
    }
}

// Подкласс для текущего счета
class CheckingAccount extends BankAccount {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, double initialBalance, double overdraftLimit) {
        super(accountNumber, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        // Проверка с учетом овердрафта
        if (amount > (balance + overdraftLimit)) {
            throw new InsufficientFundsException(amount, balance);
        }

        balance -= amount;
        System.out.println("Снято: " + amount + " со счета " + getAccountNumber());
    }

    @Override
    public String getAccountType() {
        return "Текущий счет";
    }
}

// Главный класс для демонстрации
public class BankDemo {
    public static void main(String[] args) {
        // Создаем экземпляры классов
        SavingsAccount savings = new SavingsAccount("SA001", 1000.0, 0.05);
        CheckingAccount checking = new CheckingAccount("CA001", 500.0, 200.0);

        try {
            // Демонстрация работы с сберегательным счетом
            System.out.println("=== Работа со сберегательным счетом ===");
            System.out.println("Тип счета: " + savings.getAccountType());
            System.out.println("Начальный баланс: " + savings.getBalance());

            savings.deposit(500.0);
            System.out.println("Баланс после депозита: " + savings.getBalance());

            savings.withdraw(300.0);
            System.out.println("Баланс после снятия: " + savings.getBalance());

            savings.addInterest();
            System.out.println("Баланс после начисления процентов: " + savings.getBalance());

            // Попытка снять больше, чем есть на счете (вызовет самодельное исключение)
            savings.withdraw(2000.0);
        } catch (InsufficientFundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
            System.out.println("Запрошено: " + e.getRequestedAmount() + ", доступно: " + e.getAvailableBalance());
        }

        System.out.println("\n=== Работа с текущим счетом ===");
        try {
            System.out.println("Тип счета: " + checking.getAccountType());
            System.out.println("Начальный баланс: " + checking.getBalance());

            checking.deposit(200.0);
            System.out.println("Баланс после депозита: " + checking.getBalance());

            // Снятие с текущего счета с использованием овердрафта
            checking.withdraw(800.0);
            System.out.println("Баланс после снятия (с овердрафтом): " + checking.getBalance());

            // Попытка закрыть счет с положительным балансом (вызовет стандартное исключение)
            try {
                checking.closeAccount();
            } catch (IllegalStateException e) {
                System.out.println("Не удалось закрыть счет: " + e.getMessage());
            }
        } catch (InsufficientFundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
