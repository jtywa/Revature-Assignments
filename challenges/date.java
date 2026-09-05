package challenges;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class date {
    public static void main(String[] args) {

        // 1. Print Date

        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        int year = today.getYear();
        String month = today.getMonth().name();
        int day = today.getDayOfMonth();

        System.out.println("Date: " + formattedDate);
        System.out.println("Year: " + year);
        System.out.println("Month: " + month);
        System.out.println("Day: " + day);

        // 2. Calculate Age

        Scanner sc = new Scanner(System.in);

        LocalDate parsedDate = null;
        String input = null;

        while (parsedDate == null) { //validation loop
            System.out.print("\nEnter your birth date: ");
            input = sc.nextLine();
            parsedDate = makeLocalDate(input);
            if (parsedDate == null) System.out.println("\nError: Invalid Date. Enter a valid date in the form yyyy-mm-dd");
        } 
        
        long age = ChronoUnit.YEARS.between(parsedDate, today);
        System.out.println("\nYou are " + age + " years old.");

        // resetting these to reuse in next step

        input = null;
        parsedDate = null;

        // 3. Calculate Next Birthday

        while (parsedDate == null) { // validation loop
            System.out.print("\nEnter your birthday: ");
            input = sc.nextLine();
            parsedDate = makeLocalDate(input);
            if (parsedDate == null) System.out.println("\nError: Invalid Date. Enter a valid date in the form yyyy-mm-dd");
        } 

        long daysUntilNextBirthday = getDaysUntilNextBirthday(parsedDate);
        System.out.println("\nDays until your next birthday: " + daysUntilNextBirthday);

        sc.close();
    }

    static long getDaysUntilNextBirthday(LocalDate birthday){
        LocalDate today = LocalDate.now();
        LocalDate nextBirthday = birthday.withYear(today.getYear());
        if (nextBirthday.isBefore(today) || nextBirthday.isEqual(today)) {
            nextBirthday = nextBirthday.plusYears(1);
        }
        return ChronoUnit.DAYS.between(today, nextBirthday);
    }

    static LocalDate makeLocalDate(String date){
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e){
            return null;
        }
    }
}
