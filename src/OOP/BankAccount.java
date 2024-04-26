package OOP;

class DepositException extends Exception { }
class WithdrawException extends Exception { }

public class BankAccount {

    // Instance field
    private final int accountNumber;
    protected double balance;

    // Constructor
    BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
        balance = 0.0;
    }

    // getter method
    int getAccountNumber() { return accountNumber; }

    // Bank_System methods
    boolean deposit(double amount) {
        // Amount validation
        try {
            if(amount <= 0.0)
                throw new DepositException();

            balance += amount;  // deposit
        }
        catch(DepositException depErr) {
            System.out.printf("You cannot deposit the amount of %.1f\n", amount);
            return false;
        }

        return true;
    }

    boolean withdraw(double amount) {
        // Amount validation
        try {
            if (amount > balance)
                throw new WithdrawException();

            balance -= amount;
        }
        catch(WithdrawException withErr) {
            System.out.printf("You cannot withdraw the amount of %.1f from the balance of %.1f\n", amount, this.balance);
            return false;
        }

        return true;
    }

    // Account information method
    void printAccountInfo() {
        System.out.printf("< Bank Account : %d >\n", accountNumber);
        System.out.printf("Balance -> $%d\n", (int)balance);
    }

    public static void main(String[] args) {

        // Create an instance of BankAccount
        BankAccount bank = new BankAccount(4839610);
        // Deposit $500 to the bank account
        bank.deposit(500);
        // Print the information of the account
        bank.printAccountInfo();
        System.out.println();
        // Deposit $0 to the bank account
        bank.deposit(0);
        // Print the information of the account
        bank.printAccountInfo();
        System.out.println();
        // Withdraw $100 from the account
        bank.withdraw(100);
        // Print the information of the account
        bank.printAccountInfo();
        System.out.println();
        // Withdraw $500 from the account
        bank.withdraw(500);
        // Print the information of the account
        bank.printAccountInfo();

    }

}
