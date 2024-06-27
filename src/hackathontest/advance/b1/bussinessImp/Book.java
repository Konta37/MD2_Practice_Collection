package hackathontest.advance.b1.bussinessImp;

import hackathontest.advance.b1.bussiness.IShop;
import hackathontest.advance.b1.run.BookManagement;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Book implements IShop,Comparable<Book> {
    private int bookId; //ma sach
    private String bookName; //ten sach
    private String title; //tieu de
    private int numberOfPages; //sotrang
    private Author author; //tgia
    private float importPrice;  //Gia nhap
    private float exportPrice;  //Gia ban
    private int quantity; //soluong sach
    private Boolean bookStatus; //trang thai sach

    public Book() {
    }

    public Book(int bookId, String bookName, String title, int numberOfPages, Author author, float importPrice, float exportPrice, int quantity, Boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.author = author;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.quantity = quantity;
        this.bookStatus = bookStatus;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        this.bookId = inputBookId(sc);
        this.bookName = inputBookName(sc);
        this.title = inputTitle(sc);
        this.numberOfPages = inputNumberOfPages(sc);
        //author
        this.author = inputAuthor(sc);
        this.importPrice = inputImportPrice(sc);
        this.exportPrice = RATE * this.importPrice; //export = rate * import
        this.quantity = inputQuantity(sc);
        this.bookStatus = inputStatus(sc);
    }

    @Override
    public void displayData() {
        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        System.out.printf("%-10s %-20s %-20s %-10s %-15s %-10s %-10s %-10s %-10s%n\n",
                this.bookId,this.bookName,this.title,this.numberOfPages,this.author.getAuthorName(),vndFormat.format((double) this.importPrice),vndFormat.format((double) this.exportPrice),this.quantity,this.bookStatus);
    }

    public int inputBookId(Scanner sc) {
        System.out.println("Enter book id: ");
        do {
            String bookId = sc.nextLine();
            try {
                if (Integer.parseInt(bookId) >= 0) {
                    return Integer.parseInt(bookId);
                } else {
                    System.err.println("Invalid book Id value. Must enter number > 0. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid book Id value. Try again.");
            }
        } while (true);
    }

    public String inputBookName(Scanner sc) {
        System.out.println("Enter book name: ");
        do {
            String bookName = sc.nextLine();
            if (!bookName.isEmpty()) {
                return bookName;
            } else {
                System.err.println("Book name can not empty. Try again");
            }
        } while (true);
    }

    public String inputTitle(Scanner sc) {
        System.out.println("Enter book title: ");
        return sc.nextLine();
    }

    public int inputNumberOfPages(Scanner sc) {
        System.out.println("Enter number of pages: ");
        do {
            String pages = sc.nextLine();
            try {
                if (Integer.parseInt(pages) >= 0) {
                    return Integer.parseInt(pages);
                } else {
                    System.err.println("Invalid number of pages value. Must enter number > 0. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid number of pages value. Try again.");
            }
        } while (true);
    }

    public Author inputAuthor(Scanner sc) {
        //show list author first
        System.out.println("Author List");
        System.out.printf("%-10s %-20s %-10s %-10s%n\n","Id","Name","Sex","Year");
        for (int i = 0; i < BookManagement.authorList.size(); i++){
            BookManagement.authorList.get(i).displayData();
        }
        System.out.println("Enter author that you want");
        do {
            String authorId = sc.nextLine();
            try {
                boolean isExist = false;
                int index = -1;
                for (int i = 0; i < BookManagement.authorList.size(); i++) {
                    if (BookManagement.authorList.get(i).getAuthorId() == Integer.parseInt(authorId)) {
                        isExist = true;
                        index =i;
                        break;
                    }
                }
                if (isExist){
                    return BookManagement.authorList.get(index);
                }else {
                    System.err.println("There is no id with that id " + authorId);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid author id value. Try again.");
            }

        } while (true);
    }

    public float inputImportPrice(Scanner sc) {
        System.out.println("Enter import price: ");
        do {
            String importPrice = sc.nextLine();
            try {
                if (Float.parseFloat(importPrice) >= 0) {
                    return Float.parseFloat(importPrice);
                } else {
                    System.err.println("Import price must >= 0. Try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid import price value. Try again.");
            }
        } while (true);
    }

    public int inputQuantity(Scanner sc) {
        System.out.println("Enter quantity:");
        do {
            String quantity = sc.nextLine();
            try {
                if (Integer.parseInt(quantity) > 0) {
                    return Integer.parseInt(quantity);
                } else {
                    System.err.println("Quantity must >0. Try again");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid quantity value. Try again.");
            }
        } while (true);
    }

    public Boolean inputStatus(Scanner sc){
        System.out.println("Enter book status");
        do {
            String bookStatus = sc.nextLine();
            if(bookStatus.equalsIgnoreCase("true") || bookStatus.equalsIgnoreCase("false")){
                return Boolean.parseBoolean(bookStatus);
            }else {
                System.err.println("Invalid book status value (true/false). Try again.");
            }
        }while (true);
    }

    @Override
    public int compareTo(Book o) {
        return Float.compare(this.exportPrice,o.exportPrice);
    }
}
