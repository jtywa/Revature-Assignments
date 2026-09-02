package challenges;
import java.util.Scanner;

public class calculate_test_scores_loop {
    public static void main(String[] args){
        final int NUM_SCORES = 5;
        Scanner sc = new Scanner(System.in);
        int[] scores = new int[NUM_SCORES];

        System.out.println("Please enter " + NUM_SCORES + " test scores.");

        // Loops to get each score from user and validates input
        for (int i = 0; i < NUM_SCORES; i++){
            boolean isValid = false;
            int value = 0;

            System.out.print("Score " + (i + 1) + ": ");

            while (!isValid){
                if (sc.hasNextInt()){
                    value = sc.nextInt();
                    if (value >= 0 && value <= 100){
                        isValid = true;
                    }
                    else {
                        System.out.println("Out of range. Integer must be between 0 and 100");
                    }
                } else {
                    sc.next();
                    System.out.println("Invalid input. Value must be an integer between 0 and 100");
                }
            }

            scores[i] = value;
        }

        int total = total(scores);
        double average = average(scores);
        int highest = max(scores);
        int lowest = min(scores);

        System.out.println("--------\nRESULTS\n--------");
        System.out.println("Total: " + total);
        System.out.println("Average: " + average);
        System.out.println("Highest: " + highest);
        System.out.println("Lowest: " + lowest + "\n");
        
        // List values and their letter grade
        System.out.println("Your values were:");
        for (int i = 0; i < scores.length; i++){
            System.out.println(scores[i] + " - " + letterGrade(scores[i]));
        }

        sc.close();
    }

    // returns the total sum of an array of integers
    static int total(int[] values){
        int sum = 0;
        for (int i = 0; i < values.length; i++){
            sum += values[i];
        }
        return sum;
    }

    // returns the average value of an array of integers
    static double average(int[] values){
        int count = values.length;
        int sum = 0;
        for (int i = 0; i < count; i++){
            sum += values[i];
        }
        return sum / count;
    }

    // returns maximum of an array of integers
    static int max(int[] values){
        int max = 0;
        for (int i = 0; i < values.length; i++){
            if (values[i] > max) max = values[i];
        }
        return max;
    }

    // returns minimum of an array of integers
    static int min(int[] values){
        int min = 100;
        for (int i = 0; i < values.length; i++){
            if (values[i] < min) min = values[i];
        }
        return min;
    }

    static char letterGrade(int value){
        if (value >= 90) return 'A';
        else if (value >= 80) return 'B';
        else if (value >= 70) return 'C';
        else if (value >= 60) return 'D';
        else return 'F';
    }
}
