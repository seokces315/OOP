package OOP;

import java.util.ArrayList;

class BankCustomer {

    // Instance field
    private final int IDNumber;
    private final String name;
    private ArrayList<BankAccount_N> myAccounts = new ArrayList<>();

    // Constructor
    BankCustomer(int IDNumber, String name) {
        this.IDNumber = IDNumber;
        this.name = name;
    }

    // Account methods
    Boolean addAccount(BankAccount_N acct) {
        // Duplication check
        for(BankAccount_N account : myAccounts) {
            if(account == acct) {
                System.out.println("This account is already registered!");
                return false;
            }
        }

        // Add account
        myAccounts.add(acct);
        return true;
    }

    Boolean deleteAccount(int accountNumber) {
        // Validation check
        for(BankAccount_N account : myAccounts) {
            if(account.getAccountNumber() == accountNumber) {
                myAccounts.remove(account); // remove the account
                return true;
            }
        }

        System.out.println("Invalid accountNumber!");
        return false;
    }

    // Customer information method
    void printCustomer() {
        System.out.printf("(Customer %d)\n", IDNumber);
        System.out.printf("Name : %s\n", name);
        System.out.println("### Account Information ###");
        for(BankAccount_N account : myAccounts) {
            account.printAccountInfo();
            System.out.println(); // 줄바꿈
        }
    }

}

class BankAccount_N {

    // Instance field
    private final int accountNumber;
    protected double balance;

    // Constructor
    BankAccount_N(int accountNumber) {
        this.accountNumber = accountNumber;
        balance = 0.0;
    }

    // getter method
    int getAccountNumber() { return accountNumber; }

    // Bank_System methods
    Boolean deposit(double amount) {
        // Amount validation
        if(amount <= 0.0) {
            System.out.println("Invalid amount to deposit!");
            return false;
        }

        balance += amount;  // deposit
        return true;
    }

    Boolean withdraw(double amount) {
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
    void printAccountInfo() {
        System.out.printf("< Bank Account : %d >\n", accountNumber);
        System.out.printf("Balance -> $%d\n", (int)balance);
    }

}

public class SavingsAccount_N extends BankAccount_N {

    // Class field
    private static double interestRate = 0.02;

    // Constructor
    SavingsAccount_N(int accountNumber) {
        // invokes its superclass
        super(accountNumber);
    }

    // Interest method
    double compute_interest() { return balance * interestRate; }

    // Account information method
    @Override
    void printAccountInfo() {
        super.printAccountInfo();
        System.out.printf("Interest Rate = %.1f%%\n", interestRate * 100);
    }

    public static void main(String[] args) {

        // Local field
        Boolean flag;
        double interest;

        // Create an instance of BankCustomer
        BankCustomer john = new BankCustomer(1234567, "John");
        // Print John's information
        john.printCustomer();
        System.out.println(); // 줄바꿈
        System.out.println(); // 줄바꿈

        // Create an instance of BankAccount
        BankAccount_N bank = new BankAccount_N(9740322);
        // Deposit $500
        flag = bank.deposit(500);
        if(!flag)
            System.exit(-1);
        // Print information
        System.out.println("### Deposit $500 to John's bank account! ###");
        bank.printAccountInfo();
        System.out.println(); // 줄바꿈

        // Create an instance of SavingsAccount
        SavingsAccount_N savings = new SavingsAccount_N(3901012);
        // Deposit $1000
        flag = savings.deposit(1000);
        if(!flag)
            System.exit(-1);
        // Print information
        System.out.println("### Deposit $1000 to John's savings account! ###");
        savings.printAccountInfo();
        // Withdraw $100
        flag = savings.withdraw(100);
        if(!flag)
            System.exit(-1);
        // Print information
        System.out.println("### Withdraw $100 to John's savings account! ###");
        savings.printAccountInfo();
        // Compute the interest
        interest = savings.compute_interest();
        // Add the interest by invoking deposit()
        savings.deposit(interest);
        // Print information
        System.out.println("### Deposit interest to John's savings account! ###");
        savings.printAccountInfo();
        System.out.println(); // 줄바꿈

        // Add bank account to John
        System.out.println(">>> Add account to John <<<");
        flag = john.addAccount(bank);
        if(!flag)
            System.exit(-1);
        // Add savings account to John
        flag = john.addAccount(savings);
        if(!flag)
            System.exit(-1);
        // Print John's information
        john.printCustomer();

        // Delete savings account from John
        System.out.println(">>> Delete account from John <<<");
        flag = john.deleteAccount(3901012);
        if(!flag)
            System.exit(-1);
        // Print John's information
        john.printCustomer();

    }

}
