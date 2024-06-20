package authorAndBook.feature.impl;

import authorAndBook.entity.Author;
import authorAndBook.entity.Book;
import authorAndBook.feature.IAuthor;

import java.util.ArrayList;
import java.util.List;

public class AuthorFeatureImpl implements IAuthor {
    public static List<Author> authorList = new ArrayList<>();

    @Override
    public void addAuthor(Author author) {
        authorList.add(author);
    }

    @Override
    public void updateAuthor(Author author, int indexUpdate) {
        authorList.set(indexUpdate, author);
    }

    @Override
    public void removeAuthor(int authorId) {
        int indexDelete = findIndexById(authorId);
        if (indexDelete != -1) {
            boolean isExist = false;
            for (Book b: BookFeatureImpl.booksList){
                if(b.getAuthor().getAuthorId() == authorId){
                    isExist = true;
                    break;
                }
            }
            if(!isExist){
                authorList.remove(indexDelete);
            }else {
                authorList.get(indexDelete).setStatus(false);
            }
        }else {
            System.out.println("Không tìm thấy id đó để xoá");
        }
    }

    @Override
    public int findIndexById(int authorId) {
        for (int i = 0; i < authorList.size(); i++) {
            if (authorList.get(i).getAuthorId()==authorId){
                return i;
            }
        }
        return -1;
    }

}
