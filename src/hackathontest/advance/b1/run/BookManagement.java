package hackathontest.advance.b1.run;

import hackathontest.Basic.color.ColorStorage;
import hackathontest.advance.b1.bussinessImp.Author;
import hackathontest.advance.b1.bussinessImp.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
    public static List<Author> authorList = new ArrayList<>();
    public static List<Book> bookList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //create color
        String borderColor = ColorStorage.BLUE;
        String thickBorderbottom = borderColor + "╚════════════════════════════════════════════════╝" + ColorStorage.RESET;
        String thinBorder = borderColor + "║" + ColorStorage.RESET;
        boolean isOut = true;
        do {
            String thickBorder = borderColor + "╔══════════════════════MENU══════════════════════╗" + ColorStorage.RESET;
            System.out.println(thickBorder);
            System.out.println(thinBorder + " " + borderColor + "1. Enter number of authors and information     " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "2. Enter number of books and information       " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "3. Sort books by export price                  " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "4. Search books by author name                 " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "5. Exit                                        " + thinBorder);
            System.out.println(thickBorderbottom);
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addAuthor(sc);
                    break;
                case 2:
                    addBooks(sc);
                    break;
                case 3:
                    sortBooksByExportPrice(sc);
                    break;
                case 4:
                    searchBooksByAuthorName(sc);
                    break;
                case 5:
                    isOut = false;
                    break;
                default:
                    System.err.println("Invalid choice. Try again");
            }

        } while (isOut);
    }

    public static void addAuthor(Scanner sc) {
        System.out.println("Enter number of Author that you want to add: ");
        int n;
        do {
            try {
                n = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println("Wrong type of value. Try again.");
            }
        } while (true);

        for (int i = 0; i < n; i++) {
            Author author = new Author();
            author.inputData();
            authorList.add(author);
        }
        System.out.println("Finish add " + n + " author.");
        System.out.printf("%-10s %-20s %-10s %-10s%n\n", "Id", "Name", "Sex", "Year");
        for (Author author : authorList) {
            author.displayData();
        }
        System.out.println("Finish show author.");

    }

    public static void addBooks(Scanner sc) {
        System.out.println("Enter number of Book that you want to add: ");
        int n;
        do {
            try {
                n = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println("Wrong type of value. Try again.");
            }
        } while (true);

        for (int i = 0; i < n; i++) {
            Book book = new Book();
            book.inputData();
            bookList.add(book);
        }
        System.out.println("Finish add " + n + " book.");
        System.out.printf("%-10s %-20s %-20s %-10s %-15s %-10s %-10s %-10s %-10s%n\n",
                "Id", "Name", "Title", "NumOfPages", "Author", "Import", "Export", "Quantity", "Status");
        for (Book book : bookList) {
            book.displayData();
        }
        System.out.println("Finish show book.");
    }

    public static void sortBooksByExportPrice(Scanner sc) {
        Collections.sort(bookList);
        for (Book book : bookList){
            book.displayData();
        }
        System.out.println("Finish sorting books by export price");
    }

    public static void searchBooksByAuthorName(Scanner sc) {
        System.out.println("Enter author name: ");
        String authorName = sc.nextLine();
        int count=0;
        for (Book book : bookList) {
            if(book.getAuthor().getAuthorName().contains(authorName)){
                book.displayData();
                count++;
            }
        }
        System.out.println("There are " + count + " books with author name " + authorName);
    }

}
