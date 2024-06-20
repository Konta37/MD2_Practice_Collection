package authorAndBook.run;

import authorAndBook.entity.Author;
import authorAndBook.entity.Book;
import authorAndBook.feature.IAuthor;
import authorAndBook.feature.IBook;
import authorAndBook.feature.impl.AuthorFeatureImpl;
import authorAndBook.feature.impl.BookFeatureImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    public static IAuthor authorFeature = new AuthorFeatureImpl();
    public static IBook bookFeature = new BookFeatureImpl();

    public static void main(String[] args) {
        //create list
        List<Author> authors = new ArrayList<Author>();
        List<Book> books = new ArrayList<Book>();

        menuLibrary(authors, books);
    }

    public static void menuLibrary(List<Author> listAuthors, List<Book> listBooks) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("➢ ===== MENU LIBRARIES =====");
            System.out.println("1. Quản lý tác giả");
            System.out.println("2. Quản lý quyển sách");
            System.out.println("3. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1:
                    menuAuthors(listAuthors, listBooks);
                    break;
                case 2:
                    menuBooks(listAuthors, listBooks);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Bạn phải nhập vào 1 hoặc 2");
            }
        } while (true);
    }

    public static void menuAuthors(List<Author> listAuthors, List<Book> listBooks) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("➢ ===== MENU AUTHOR =====");
            System.out.println("1. Hiển thị danh sách các tác giả");
            System.out.println("2. Thêm mới tác giả");
            System.out.println("3. Cập nhật thông tin tác giả.");
            System.out.println("4. Tìm kiếm thông tin tác giả theo tên.");
            System.out.println("5. Thống kê ca tác giả có bao nhiêu quyển sách (Sử dụng Map).");
            System.out.println("6. Xoá tác giả"); //xoá tác giả nếu tác giả đó chưa có sách nào, còn nếu có rồi thì chuyển status thành false
            System.out.println("7. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1:
                    handleShowAllAuthors();
                    break;
                case 2:
                    handleAddNewAuthor(scanner);
                    break;
                case 3:
                    handleUpdateAuthorById(scanner,listAuthors,listBooks);
                    break;
                case 4:
                    handleSearchAuthorByName(scanner);
                    break;
                case 5:
                    handleStatistical();
                    break;
                case 6:
                    handleRemoveAuthorById(scanner);
                    break;
                case 7:
                    menuLibrary(listAuthors, listBooks);
                default:
                    System.out.println("Bạn phải nhập vào 1 tới 7");
            }
        } while (true);
    }

    public static void menuBooks(List<Author> listAuthors, List<Book> listBooks) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("➢ ===== MENU BOOK =====");
            System.out.println("1. Hiển thị danh sách quyển sách");
            System.out.println("2. Thêm thông tin sách");
            System.out.println("3. Cập nhật thông tin sách.");
            System.out.println("4. Tìm kiếm thông tin sách theo tên sách.");
            System.out.println("5. Tìm kiếm thông tin sách theo giá từ khoảng a->b.");
            System.out.println("6. Xoá thông tin sách");
            System.out.println("7. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1:
                    handleShowAllBooks();
                    break;
                case 2:
                    handleAddNewBook(scanner);
                    break;
                case 3:
                    handleUpdateBookById(scanner);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    menuLibrary(listAuthors, listBooks);
                default:
                    System.out.println("Bạn phải nhập vào 1 tới 7");
            }
        } while (true);
    }


    public static void handleAddNewAuthor(Scanner sc) {
        System.out.println("Nhập vào số lượng muốn thêm: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            Author author = new Author();
            author.inputData(sc, AuthorFeatureImpl.authorList, BookFeatureImpl.booksList);
            authorFeature.addAuthor(author);
        }
    }

    public static void handleShowAllAuthors() {
        if (AuthorFeatureImpl.authorList.isEmpty()) {
            System.err.println("author is empty");
            return;
        }
        System.out.printf("%-10s %-20s %-20s %-10s %-15s%n\n", "Author ID", "Author Name", "Description", "Age", "Status");
        for (Author author : AuthorFeatureImpl.authorList) {

            author.displayData();
        }
    }

    public static void handleRemoveAuthorById(Scanner sc) {
        System.out.println("Nhập id của tác giả để xoá");
        do {
            String idRemove = sc.nextLine();
            try {
                int indexRemove = authorFeature.findIndexById(Integer.parseInt(idRemove));
                if (indexRemove < 0) {
                    System.err.println("Không tồn tại id của tác giả để xoá");
                    return;
                }
                authorFeature.removeAuthor(indexRemove);
                return;
            } catch (NumberFormatException e) {
                System.err.println("Id nhập vào ko phải là số. Mời nhập lại");
            }
        } while (true);

    }

    //update Author by Id
    public static void handleUpdateAuthorById(Scanner sc, List<Author> authors, List<Book> books) {
        System.out.println("Nhập id của tác giả: ");
        int idUpdate = Integer.parseInt(sc.nextLine());
        int indexUpdate = authorFeature.findIndexById(idUpdate);
        if (indexUpdate < 0) {
            System.out.println("Không tồn tại tác giả với id vừa nhập");
            return;
        }
        boolean isLoop = true;
        do {
            Author authorUpdate = AuthorFeatureImpl.authorList.get(indexUpdate);
            System.out.println("1. Cập nhật tên.");
            System.out.println("2. Cập nhật mô tả của tác giả");
            System.out.println("3. Cập nhật tuổi của tác giả");
            System.out.println("4. Cập nhật trạng thái (true/false)");
            System.out.println("5. Thoát");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Nhập tên để cập nhật: ");
                    authorUpdate.setAuthorName(authorUpdate.inputAuthorName(sc));
                    break;
                case 2:
                    System.out.println("Nhập mô tả để cập nhật: ");
                    authorUpdate.setDescription(authorUpdate.inputDescription(sc));
                    break;
                case 3:
                    System.out.println("Nhập tuổi của tác giả để cập nhật: ");
                    authorUpdate.setAge(authorUpdate.inputAge(sc));
                    break;
                case 4:
                    System.out.println("Nhập trạng thái để cập nhật (true/false): ");
                    authorUpdate.setStatus(authorUpdate.inputStatus(sc));
                    break;
                case 5:
                    isLoop = false;
                    break;
                default:
                    System.err.println("Nhập từ 1-5");
            }
            authorFeature.updateAuthor(authorUpdate,indexUpdate);
        }while (isLoop);
    }

    public static void handleSearchAuthorByName(Scanner sc){
        System.out.println("Nhập tên của tác giả để tìm: ");
        String authorName = sc.nextLine();
        for (Author author : AuthorFeatureImpl.authorList) {
            if (author.getAuthorName().toLowerCase().contains(authorName.toLowerCase())) {
                author.displayData();
            }
        }
    }

    public static void handleStatistical(){
        for (Author author : AuthorFeatureImpl.authorList) {
            int count = 0;
            for (Book book : BookFeatureImpl.booksList){
                if (book.getAuthor().getAuthorId() == author.getAuthorId()){
                    count++;
                }
            }
            author.displayData();
            System.out.println(author.getAuthorName() + " : " + count + " sách.");
        }
    }

    public static void handleShowAllBooks() {
        if (BookFeatureImpl.booksList.isEmpty()) {
            System.err.println("book is empty");
            return;
        }
        System.out.printf("%-10s %-20s %-20s %-10s %-15s%n\n", "Book ID", "Book Name", "Export Price", "Created", "Author");
        for (Book book : BookFeatureImpl.booksList) {
            book.displayData();
        }
    }

    public static void handleAddNewBook(Scanner sc) {
        System.out.println("Nhập vào số lượng muốn thêm: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            Book book = new Book();
            book.inputData(sc, AuthorFeatureImpl.authorList, BookFeatureImpl.booksList);
            bookFeature.addBook(book);
        }
    }

    public static void handleUpdateBookById(Scanner sc) {
        System.out.println("Nhập id của sách: ");
        String idUpdate = sc.nextLine();
        int indexUpdate = bookFeature.findIndexBookById(idUpdate);
        if (indexUpdate < 0) {
            System.out.println("Không tồn tại sách với id vừa nhập");
            return;
        }
        boolean isLoop = true;
        do {
            Book bookUpdate = BookFeatureImpl.booksList.get(indexUpdate);
            System.out.println("1. Cập nhật tên sách.");
            System.out.println("2. Cập nhật export Price");
            System.out.println("3. Cập nhật tác giả của sách để cập nhật");
            System.out.println("4. Cập nhật trạng thái (0: Đang bán/1: Hết hàng/2: Không bán)");
            System.out.println("5. Thoát");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Nhập tên để cập nhật: ");
                    bookUpdate.setBookName(bookUpdate.inputBookName(sc, BookFeatureImpl.booksList));
                    break;
                case 2:
                    System.out.println("Nhập export Price để cập nhật: ");
                    bookUpdate.setExportPrice(bookUpdate.inputExportPrice(sc));
                    break;
                case 3:
                    System.out.println("Nhập tác giả của sách để cập nhật: ");
                    bookUpdate.setAuthor(bookUpdate.inputAuthor(sc,BookFeatureImpl.booksList,AuthorFeatureImpl.authorList));
                    break;
                case 4:
                    System.out.println("Nhập trạng thái để cập nhật (0: Đang bán/1: Hết hàng/2: Không bán): ");
                    bookUpdate.setStatus(bookUpdate.inputStatus(sc));
                    break;
                case 5:
                    isLoop = false;
                    break;
                default:
                    System.err.println("Nhập từ 1-5");
            }
            bookFeature.updateBook(bookUpdate,indexUpdate);
        }while (isLoop);
    }
}
