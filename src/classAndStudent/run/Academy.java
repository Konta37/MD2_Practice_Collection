package classAndStudent.run;

import java.util.Scanner;

public class Academy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("➢ ===== ACADEMY =====");
            System.out.println("1. Class Management");
            System.out.println("2. Student Management");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    ClassManagement.menuClass(sc);
                    break;
                case 2:
                    StudentManagement.menuStudent(sc);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Please choose between 1 -> 3");
            }
        } while (true);
    }
}
