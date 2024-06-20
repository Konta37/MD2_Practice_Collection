package authorAndBook.feature;

import authorAndBook.entity.Author;
import authorAndBook.entity.Book;

import java.util.List;
import java.util.Scanner;

public interface IInputAndShow {
    void inputData(Scanner scanner, List<Author> authors, List<Book> books);
    void displayData();
}
