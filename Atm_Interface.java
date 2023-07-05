// Task 3 
// Atm Interface We have all come across ATMs in our cities and it is built on Java.This complex project consists of five different classes and is a console-based application.When the system starts the user is prompted with user id and user pin.On entering the details successfully,then ATM functionalities are unlocked.The project allows to perform following operations:
// Transactions History
// Withdraw
// Deposit
// Transfer
// Quit

// I have added the comments for better understanding of the code

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // import scanner class for user input

public class Atm_Interface {
    private static final String USER_ID = "12345"; // adding my default user id and pin
    private static final String USER_PIN = "1234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean enter = true; // boolean to check if user has entered correct credentials
        System.out.println("Welcome to the ATM");
        while (enter) {

            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter User PIN: ");
            String userPin = scanner.nextLine();

            if (userId.equals(USER_ID) && userPin.equals(USER_PIN)) {
                System.out.println("Login Successful");
                showMenu(scanner); // show menu if user enters correct credentials
                enter = false;
            } else {
                System.out.println("Invalid credentials. Try Again...");
                enter = true;
            }
        }

        scanner.close();
    }

    public static void showMenu(Scanner scanner) {
        // show menu
        boolean quit = false; // boolean to check if user has quit
        double bank_balance = 10000.00; // default balance
        System.out.println("Default Balance is: " + bank_balance);
        List<Double> withdraw = new ArrayList<Double>(); // list to store withdraw amounts history
        List<Double> deposit = new ArrayList<Double>(); // list to store deposit amounts history

        while (!quit) {
            System.out.println("\n\nATM Menu");
            System.out.println("Enter 1 for Transactions History");
            System.out.println("Enter 2 for Withdraw");
            System.out.println("Enter 3 for Deposit");
            System.out.println("Enter 4 for Transfer");
            System.out.println("Enter 5 for Quit");
            System.out.print("Enter your choice: ");

            try { // try catch block to handle exceptions
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        TransactionHistory.showTransactionHistory(bank_balance, withdraw, deposit);
                        break;
                    case 2:
                        System.out.println("\nCurrent Balance: " + bank_balance);
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        double res = Withdraw.withdrawAmount(bank_balance, withdrawAmount, withdraw, deposit);
                        if (res != 0) {
                            bank_balance = res;
                            withdraw.add(withdrawAmount);
                        }
                        break;
                    case 3:
                        System.out.print("\nEnter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        bank_balance = Deposit.depositAmount(bank_balance, depositAmount, withdraw, deposit);
                        deposit.add(depositAmount);
                        break;
                    case 4:
                        System.out.print("\nEnter recipient account number: ");
                        String recipientAccount = scanner.nextLine();
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();
                        double transfer = Transfer.transferAmount(bank_balance, recipientAccount, transferAmount,
                                withdraw, deposit);
                        if (transfer != 0) {
                            bank_balance = transfer;
                            withdraw.add(transferAmount);
                        }
                        break;
                    case 5:
                        System.out.println("\nLogging Out...\nThank you for using the ATM. Goodbye!");
                        quit = true;
                        break;
                    default:
                        System.out.println("\nInvalid choice or input. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("\nInvalid choice or input. Please try again.");
                scanner.nextLine();
            }
        }
    }
}

class TransactionHistory {
    public static void showTransactionHistory(double bank_balance, List<Double> withdraw, List<Double> deposit) {
        System.out.println("\n\nDisplaying transaction history...");
        System.out.println("Current Balance: " + bank_balance);
        System.out.println("\nwithdrawl history: ");
        // for each loop to iterate through the list
        for (double i : withdraw) {
            System.out.print("-" + i + ", ");
        }
        System.out.println("\nDeposit history: ");
        for (double i : deposit) {
            System.out.print("+" + i + ", ");
        }

    }
}

class Withdraw {
    public static double withdrawAmount(double bank_balance, double amount, List<Double> withdraw,
            List<Double> deposit) {
        if (amount > bank_balance) {
            System.out.println("\nInsufficient Balance");
            return 0;
        } else {
            bank_balance = bank_balance - amount;
            System.out.println("Withdrawing amount: " + amount);
            System.out.println("Balance: " + bank_balance);
            return bank_balance;

        }

    }
}

class Deposit {
    public static double depositAmount(double bank_balance, double amount, List<Double> withdraw,
            List<Double> deposit) {
        System.out.println("Current Balance: " + bank_balance);
        System.out.println("Depositing amount: " + amount);
        bank_balance = bank_balance + amount;
        System.out.println("New Balance: " + bank_balance);
        return bank_balance;

    }
}

class Transfer {
    public static double transferAmount(double bank_balance, String recipientAccount, double amount,
            List<Double> withdraw, List<Double> deposit) {
        if (amount <= 0) {
            System.out.println("Invalid amount, try again");
            return 0;
        } else if (amount < bank_balance) {
            System.out.println("Transferring amount: " + amount + " to account: " + recipientAccount);
            bank_balance = bank_balance - amount;
            System.out.println("New Balance: " + bank_balance);
            return bank_balance;

        } else {
            System.out.println("Insufficient Balance");
            return 0;
        }
    }
}

// Sample Output:
// Welcome to the ATM
// Enter User ID: 12345
// Enter User PIN: 1234
// Login Successful
// Default Balance is: 10000.0

// ATM Menu
// Enter 1 for Transactions History
// Enter 2 for Withdraw
// Enter 3 for Deposit
// Enter 4 for Transfer
// Enter 5 for Quit
// Enter your choice: 2

// Current Balance: 10000.0
// Enter amount to withdraw: 800
// Withdrawing amount: 800.0
// Balance: 9200.0

// ATM Menu
// Enter 1 for Transactions History
// Enter 2 for Withdraw
// Enter 3 for Deposit
// Enter 4 for Transfer
// Enter 5 for Quit
// Enter your choice: 3

// Enter amount to deposit: 2000
// Current Balance: 9200.0
// Depositing amount: 2000.0
// New Balance: 11200.0

// ATM Menu
// Enter 1 for Transactions History
// Enter 2 for Withdraw
// Enter 3 for Deposit
// Enter 4 for Transfer
// Enter 5 for Quit
// Enter your choice: 4

// Enter recipient account number: qwe4321
// Enter transfer amount: 3000
// Transferring amount: 3000.0 to account: qwe4321
// New Balance: 8200.0

// ATM Menu
// Enter 1 for Transactions History
// Enter 2 for Withdraw
// Enter 3 for Deposit
// Enter 4 for Transfer
// Enter 5 for Quit
// Enter your choice: 1

// Displaying transaction history...
// Current Balance: 8200.0

// withdrawl history:
// -800.0, -3000.0,
// Deposit history:
// +2000.0,

// ATM Menu
// Enter 1 for Transactions History
// Enter 2 for Withdraw
// Enter 3 for Deposit
// Enter 4 for Transfer
// Enter 5 for Quit
// Enter your choice: 5

// Logging Out...
// Thank you for using the ATM. Goodbye!
