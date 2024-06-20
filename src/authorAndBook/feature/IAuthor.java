package authorAndBook.feature;

import authorAndBook.entity.Author;

public interface IAuthor {
    void addAuthor(Author author);
    void updateAuthor(Author author, int indexUpdate);
    void removeAuthor(int authorId);
    int findIndexById(int authorId);
}
