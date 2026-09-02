package challenges;
import java.util.Scanner;

public class repl {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        double balance = 0;
        int command;
        boolean exit = false;

        // this loop takes input until a valid command is received, then
        // performs the chosen function. invalid commands and dollar amounts
        // are handled with error messages for the user.
        while (!exit){
            printMenu();
            
            if (input.hasNextInt()){
                command = input.nextInt();
                switch (command){

                    case 1: // CHECK BALANCE
                        message("Balance: $" + balance);
                        break;

                    case 2: // DEPOSIT
                        message("Enter amount to deposit");
                        if (input.hasNextDouble()){
                            double amount = input.nextDouble();
                            if (amount <= 0) {
                                message("Error: Amount to deposit must be positive");
                            } else {
                                balance += amount;
                                message("Successfully deposited $" + amount);
                            }
                        } else {
                            message("Invalid amount");
                            input.next();
                        }
                        break;

                    case 3: // WITHDRAW
                        message("Enter amount to withdraw");
                        if (input.hasNextDouble()){
                            double amount = input.nextDouble();
                            if (amount <= 0){
                                message("Error: amount to deposit must be positive");
                            } else if (amount > balance){
                                message("Error: Insufficient funds");
                            } else {
                                balance -= amount;
                                message("Successfully withdrew $" + amount);
                            }
                        } else {
                            message("Invalid amount");
                            input.next();
                        }
                        break;

                    case 4: // EXIT
                        exit = true;
                        message("Exiting the program");
                        break;

                    default: // INVALID COMMAND
                        message("Error. Command out of range");
                        break;
                }
            } else {
                message("Error: Invalid command");
                input.next();
            }
        }

        input.close();
    }

    static void printMenu(){
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("\nEnter a command: ");
    }

    static void message(String text){
        System.out.println("\n------------------------------\n" + text + "\n------------------------------\n");
    }
}
