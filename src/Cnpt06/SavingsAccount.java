package Cnpt06;

class BankAccount {

    // Instance field
    protected final int accountNumber;
    protected double balance;

    // Constructor
    BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
        balance = 0.0;
    }

    // Bank_System methods
    boolean deposit(double amount) {
        // Amount validation
        if(amount <= 0.0) {
            System.out.println("Invalid amount to deposit!");
            return false;
        }

        balance += amount;  // deposit
        return true;
    }

    boolean withdraw(double amount) {
        // Amount validation
        if (amount <= 0.0) {
            System.out.println("Invalid amount to withdraw!");
            return false;
        }
        else if(amount > balance) {
            System.out.println("Withdrawal is bigger than your balance!");
            return false;
        }
        else {
            balance -= amount;  // withdraw
            return true;
        }
    }

    // Account information method
    String get_Account_Info() {
        // local field
        StringBuilder sb = new StringBuilder();

        // Build the information
        sb.append("< Bank_Account : ").append(accountNumber).append(" >\n");
        sb.append("Balance -> $").append((int)balance);

        return sb.toString();
    }

}

public class SavingsAccount extends BankAccount {

    // Class field
    private static double interestRate = 0.02;

    // Constructor
    SavingsAccount(int accountNumber) {
        // invokes its superclass
        super(accountNumber);
    }

    // Interest method
    double compute_interest() { return balance * interestRate; }

    public static void main(String[] args) {

        // Local field
        boolean flag;
        String info;
        double interest;

        // Create an instance of Bank_Account
        BankAccount bank = new BankAccount(9740322);
        // Deposit $100
        flag = bank.deposit(100);
        if(!flag)
            System.exit(-1);
        // Print information
        System.out.println("##### Deposit $100 to 1st instance! #####");
        info = bank.get_Account_Info();
        System.out.println(info);
        System.out.println(); // 줄바꿈

        // Create an instance of Savings_Account
        SavingsAccount savings = new SavingsAccount(3901012);
        // Deposit $200
        flag = savings.deposit(200);
        if(!flag)
            System.exit(-1);
        // Print information
        System.out.println("##### Deposit $200 to 2nd instance! #####");
        info = savings.get_Account_Info();
        System.out.println(info);
        System.out.println(); // 줄바꿈
        // Withdraw $50
        flag = savings.withdraw(50);
        if(!flag)
            System.exit(-1);
        // Print information
        System.out.println("##### Withdraw $50 to 2nd instance! #####");
        info = savings.get_Account_Info();
        System.out.println(info);
        System.out.println(); // 줄바꿈
        // Compute the interest
        interest = savings.compute_interest();
        // Print the interest
        System.out.printf("Current account's interest => $%d\n", (int)interest);
        // Add the interest by invoking deposit()
        savings.deposit(interest);
        // Print information
        System.out.println("##### Deposit computed interest to 2nd instance! #####");
        info = savings.get_Account_Info();
        System.out.println(info);

    }

}
