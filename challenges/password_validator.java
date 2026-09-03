package challenges;
import java.util.Scanner;

public class password_validator {
    public static void main(String[] args){
        System.out.println("Please create a password.\n");
        System.out.println("The password must:");
        System.out.println("\tBe at least 8 characters");
        System.out.println("\tContain at least one uppercase letter");
        System.out.println("\tContain at least one lowercase letter");
        System.out.print("\tContain at least one number\n\n");
    
        Scanner input = new Scanner(System.in);
        String password;

        do {
            System.out.print("> ");
            password = input.nextLine();
        } while (!isValid(password));

        System.out.println("\nPassword accepted!");
    }

    static boolean isValid(String pwd){
        if (pwd.length() >= 8 && !pwd.equals(pwd.toLowerCase()) && !pwd.equals(pwd.toUpperCase()) && pwd.matches(".*\\d.*")) return true;
        
        System.out.println("\nPassword rejected:");
        if (pwd.length() < 8) {
            System.out.println("- Must be at least 8 characters");
        }
        if (pwd.equals(pwd.toLowerCase())) {
            System.out.println("- Must contain an uppercase letter");
        }
        if (pwd.equals(pwd.toUpperCase())) {
            System.out.println("- Must contain a lowercase letter");
        }
        if (!pwd.matches(".*\\d.*")) {
            System.out.println("- Must contain a number");
        }
        System.out.println("\n");
        return false;
    }
}
