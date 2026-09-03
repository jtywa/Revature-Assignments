package challenges;
import java.util.Scanner;

public class word_analyzer {
    public static void main(String[] args) {
        System.out.print("Enter word(s): ");
        Scanner input = new Scanner(System.in);
        String word = input.nextLine();

        int characters = word.length(), vowels = 0, consonants = 0, digits = 0, spaces = 0;

        // loop to check what character is, then increment that category
        for (int i = 0; i < word.length(); i++){
            char c = word.toLowerCase().charAt(i);
            if (Character.isLetter(c)){
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
                    vowels++;
                } else {
                    consonants++;
                }
            } 
            else if (Character.isDigit(c)) digits++;
            else if (Character.isWhitespace(c)) spaces++;
        }

        System.out.println("\nCharacters: " + characters);
        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
        System.out.println("Digits: " + digits);
        System.out.println("Spaces: " + spaces);
    }
}
