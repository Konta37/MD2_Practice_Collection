package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static Date inputDateOfBirth(Scanner sc) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false); // Ensures strict parsing
        Date dateOfBirth = null;

        while (dateOfBirth == null) {
            System.out.println("Enter Student Date of Birth (dd-MM-yyyy): ");
            String dateInput = sc.nextLine();

            try {
                dateOfBirth = dateFormat.parse(dateInput);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
            }
        }

        return dateOfBirth;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Date dateOfBirth = inputDateOfBirth(sc);
        System.out.println("Entered Date of Birth: " + new SimpleDateFormat("dd-MM-yyyy").format(dateOfBirth));
        sc.close();
    }
}