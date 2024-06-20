package authorAndBook.entity;

import authorAndBook.feature.IInputAndShow;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Book implements IInputAndShow {
    private String bookId;
    private String bookName;
    private double exportPrice;
    private Date created;
    private Author author;
    private byte status; //0-Đang bán, 1-hết hàng, 2-Không bán.

    public Book() {
    }

    public Book(String bookId, String bookName, double exportPrice, Date created, Author author, byte status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.exportPrice = exportPrice;
        this.created = created;
        this.author = author;
        this.status = status;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }


    @Override
    public void inputData(Scanner sc, List<Author> authors, List<Book> books) {
        this.bookId = inputBookId(sc, books);
        this.bookName = inputBookName(sc, books);
        this.exportPrice = inputExportPrice(sc);
        this.created = inputCreated();
        this.author = inputAuthor(sc, books, authors);
    }

    @Override
    public void displayData() {
        System.out.printf("%-10s %-20s %-20s %-10s %-15s%n\n", "Book ID", "Book Name", "Export Price", "Created", "Author");
        System.out.printf("%-10s %-20s %-20s %-10s %-15s%n\n", getBookId(), getBookName(), getExportPrice(), getCreated(), getAuthor().getAuthorName());

    }

    public String inputBookId(Scanner sc, List<Book> books) {
        System.out.println("Nhập id của sách: ");
        do {
            String bookId = sc.nextLine();
            String regex = "B\\d{3}";
            if (bookId.matches(regex)) {
                boolean isCheck = false;
                for (int i = 0; i < books.size(); i++) {
                    if (books.get(i).getBookId().equals(bookId)) {
                        isCheck = true;
                        break;
                    }
                }
                if (isCheck) {
                    System.err.println("Id của sách đã bị trùng. Mời nhập lại.");
                } else {
                    return bookId;
                }
            } else {
                System.err.println("Id của sách bị nhập sai(Bxxx). Mời nhập lại.");
            }
        } while (true);
    }

    public String inputBookName(Scanner sc, List<Book> books) {
        System.out.println("Nhập tên của sách.");
        do {
            String bookName = sc.nextLine();
            if (!bookName.isEmpty()) {
                boolean isCheck = false;
                for (Book book : books) {
                    if (book.getBookName().equals(bookName)) {
                        isCheck = true;
                        break;
                    }
                }
                if (isCheck) {
                    System.err.println("Tên của sách vừa nhập đã bị trùng. Mời nhập lại");
                } else {
                    return bookName;
                }
            } else {
                System.err.println("Tên của sách vừa nhập bị lỗi. Mời nhập lại");
            }
        } while (true);
    }

    public double inputExportPrice(Scanner sc) {
        System.out.println("Nhập giá sách để bán ra: ");
        do {
            String exportPrice = sc.nextLine();
            try {
                if (Double.parseDouble(exportPrice) > 0) {
                    return Double.parseDouble(exportPrice);
                } else {
                    System.err.println("Giá nhập vào bị lỗi(phải lớn hơn 0). Mời nhập lại");
                }
            } catch (NumberFormatException e) {
                System.err.println("Giá nhập vào bị lỗi. Chỉ nhận số là số đó > 0.");
            }
        } while (true);
    }

    public Date inputCreated() {
        LocalDate localDate = LocalDate.now();
        Date created = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return created;
    }

    public Author inputAuthor(Scanner sc, List<Book> books, List<Author> authors) {
        System.out.println("Nhập id của tác giả:");
        int count = 2;
        do {
            String authorId = sc.nextLine();
            //check input is number or not
            try {
                if (Integer.parseInt(authorId) >= 0) {
                    //check if there any author with that id
                    int index = -1;
                    for (int i = 0; i < authors.size(); i++) {
                        if (authors.get(i).getAuthorId() == Integer.parseInt(authorId)) {
                            index = i;
                            break;
                        }
                    }
                    if (index >= 0) {
                        return authors.get(index);
                    } else {

                        if (count != 0) {
                            System.err.printf("Không tìm thấy id author. Còn %d lượt để nhập lại!\n", count);
                            count--;
                        } else {
                            inputData(sc, authors, books);
                        }
                        System.out.println("Không có id tác giả đó. Mời nhập lại!");
                    }
                } else {
                    System.err.println("Lỗi nhập id của tác giả. Mời nhập lại.");

                }
            } catch (NumberFormatException e) {
                System.err.println("Lỗi nhập id tác giả. Phải nhập dạng số");
            }
        } while (true);

    }

    public byte inputStatus(Scanner sc) {
        System.out.println("Nhập trạng thái của sách");
        do {
            String status = sc.nextLine();
            try {
                if (Byte.parseByte(status) >= 0 && Byte.parseByte(status) < 4) {
                    return Byte.parseByte(status);
                } else {
                    System.err.println("Trạng thái nhập vào bị lỗi(trong khoảng 0-3). Mời nhập lại");
                }
            } catch (NumberFormatException e) {
                System.err.println("Trạng thái nhập vào bị lỗi. Chỉ nhận số là số đó (0-3).");
            }
        } while (true);
    }

}
