package challenges;

public class control_flow {
    public static void main(String[] args){
        int score = 75;
        char grade = 'B';

        if (score >= 50) System.out.println("Passed");
        else System.out.println("Failed");

        char calculatedGrade;

        if (score >= 90) calculatedGrade = 'A';
        else if (score >= 75 && score <= 89) calculatedGrade = 'B';
        else if (score >= 60 && score <= 74) calculatedGrade = 'C';
        else calculatedGrade = 'D';

        System.out.println(calculatedGrade);
    }
}
