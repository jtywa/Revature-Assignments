package challenges;
import java.util.Scanner;
import java.util.Random;

// TO DO: add input validation

public class repl_app {
    public static void main(String[] args){
        System.out.print("Welcome to my REPL App!\n\n");
        Scanner input = new Scanner(System.in);
        String command;
        boolean looping = true;

        while (looping){
            System.out.print("> ");
            command = input.nextLine();
            looping = executeCommand(command, input);
        }

        input.close();
    }
    
    // Parses the command and runs the respective handler method
    static boolean executeCommand(String command, Scanner input){
        switch(command){
            case "help":
                help();
                break;
            case "add", "subtract", "multiply", "divide":
                arithmetic(command, input);
                break;
            case "random":
                random(input);
                break;
            case "reverse":
                reverse(input);
                break;
            case "quit":
                return false;
            default:
                System.out.println("Invalid command");
        }
        return true;
    }

    // Displays a list of available commands
    static void help(){
        System.out.println("\nAvailable commands:");
        System.out.println("add\nsubtract\nmultiply\ndivide\nrandom\nreverse\nquit");
    }

    // Performs arithmetic on operands according to which command was passed
    static void arithmetic(String cmd, Scanner input){
        int[] nums = new int[2];
        nums = getOperands(input);
        double result = 
            cmd.equals("add") ? nums[0] + nums[1] : 
            cmd.equals("subtract") ? nums[0] - nums[1] :
            cmd.equals("multiply") ? nums[0] * nums[1] :
            cmd.equals("divide") ? (nums[0] * 1.0 / nums[1]) : -1;
        if (result % 1 == 0) {
            System.out.println("Result: " + (int) result);
        } else {
            System.out.println("Result: " + result);
        }
        input.nextLine();
    }

    // Takes a min and max and outputs a random number between them (inclusive)
    static void random(Scanner input){
        int min, max;
        System.out.print("Minimum: ");
        min = input.nextInt();
        System.out.print("Maximum: ");
        max = input.nextInt();

        Random r = new Random();
        int randomNum;
        randomNum = r.nextInt((max - min) + 1) + min;
        System.out.println("Random number: " + randomNum);
        input.nextLine();
    }

    // Takes a string and reverses it
    static void reverse(Scanner input){
        System.out.print("Enter text: ");
        String text = input.nextLine();
        String reversed = new StringBuilder(text).reverse().toString();
        System.out.println(reversed);
    }

    // Prompts the user for two operands
    static int[] getOperands(Scanner input){
        int nums[] = new int[2];
        System.out.print("First number: ");
        nums[0] = input.nextInt();
        System.out.print("Second number: ");
        nums[1] = input.nextInt();
        return nums;
    }
}
