package challenges;
public class calculator {
    public static void main(String[] args) {
        double num1 = 7, num2 = 3;
        char operator = '/';
        String again = "y";

        boolean error = false; // error flag
        double result = 0;

        while (again == "y"){
            if (operator == '+'){
                result = num1 + num2;
            }
            else if (operator == '-'){
                result = num1 - num2;
            }
            else if (operator == '*'){
                result = num1 * num2;
            }
            else if (operator == '/'){
                if (num2 == 0) {
                    error = true;
                }
                else {
                    result = num1 / num2;
                }
            }
            again = "n";
        }

        // Print result or error
        if (error) System.out.println("Cannot divide by zero");
        else System.out.println("Result: " + result);
    }
}
