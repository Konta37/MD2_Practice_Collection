package authorAndBook.feature;

import authorAndBook.entity.Book;

public interface IBook {
    void addBook(Book book);
    void updateBook(Book book, int indexUpdate);
    void removeBook(String bookId);
    int findIndexBookById(String bookId);
}
