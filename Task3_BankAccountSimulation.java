import java.util.ArrayList;
import java.util.Scanner;

/**
 * Task 3: Bank Account Simulation
 * ---------------------------------
 * Simulates a simple bank account with deposit, withdrawal, and balance
 * check operations. A BankAccount class holds the account's fields and
 * methods, validates that withdrawals never exceed the balance, and
 * keeps a transaction history that is printed at the end.
 */
public class Task3_BankAccountSimulation {

    // Class representing a bank account: fields + behavior together (encapsulation)
    static class BankAccount {
        private String accountHolder;
        private double balance;
        private ArrayList<String> transactionHistory;

        BankAccount(String accountHolder, double openingBalance) {
            this.accountHolder = accountHolder;
            this.balance = openingBalance;
            this.transactionHistory = new ArrayList<>();
            transactionHistory.add(String.format("Account opened with balance: %.2f", openingBalance));
        }

        // Deposits must be positive amounts
        void deposit(double amount) {
            if (amount <= 0) {
                System.out.println("Deposit amount must be positive.");
                return;
            }
            balance += amount;
            transactionHistory.add(String.format("Deposited: %.2f | Balance: %.2f", amount, balance));
            System.out.println("Deposit successful. New balance: " + balance);
        }

        // Withdrawal is validated so it can never exceed the available balance
        void withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Withdrawal amount must be positive.");
                return;
            }
            if (amount > balance) {
                System.out.println("Insufficient balance! Available: " + balance);
                transactionHistory.add(String.format("Failed withdrawal attempt: %.2f (Insufficient funds)", amount));
                return;
            }
            balance -= amount;
            transactionHistory.add(String.format("Withdrew: %.2f | Balance: %.2f", amount, balance));
            System.out.println("Withdrawal successful. New balance: " + balance);
        }

        // Simply reports the current balance
        void checkBalance() {
            System.out.println("Current balance for " + accountHolder + ": " + balance);
        }

        // Prints every transaction recorded so far, in order
        void printTransactionHistory() {
            System.out.println("\n===== Transaction History for " + accountHolder + " =====");
            for (String record : transactionHistory) {
                System.out.println("- " + record);
            }
            System.out.println("Final Balance: " + balance);
            System.out.println("=========================================");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter opening balance: ");
        double openingBalance = sc.nextDouble();

        BankAccount account = new BankAccount(name, openingBalance);

        boolean running = true;
        while (running) {
            System.out.println("\n----- Bank Menu -----");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit and Print Transaction History");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    account.deposit(sc.nextDouble());
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    account.withdraw(sc.nextDouble());
                    break;

                case 3:
                    account.checkBalance();
                    break;

                case 4:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1-4.");
            }
        }

        // Print the full transaction history before the program ends
        account.printTransactionHistory();
        sc.close();
    }
}
