package authorAndBook.feature.impl;

import authorAndBook.entity.Book;
import authorAndBook.feature.IBook;

import java.util.ArrayList;
import java.util.List;

public class BookFeatureImpl implements IBook {
    public static List<Book> booksList = new ArrayList<Book>();

    @Override
    public void addBook(Book book) {
        booksList.add(book);
    }

    @Override
    public void updateBook(Book book, int indexUpdate) {
        booksList.set(indexUpdate, book);
    }

    @Override
    public void removeBook(String bookId) {
        int indexDelete = findIndexBookById(bookId);
        if (indexDelete != -1) {
            booksList.remove(indexDelete);
        }else {
            System.out.println("Không tìm thấy id đó để xoá");
        }
    }

    @Override
    public int findIndexBookById(String bookId) {
        for (int i = 0; i < booksList.size(); i++) {
            if (booksList.get(i).getBookId().equals(bookId)){
                return i;
            }
        }
        return -1;
    }

}
