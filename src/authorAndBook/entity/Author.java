package authorAndBook.entity;

import authorAndBook.feature.impl.AuthorFeatureImpl;
import authorAndBook.feature.IInputAndShow;

import java.util.List;
import java.util.Scanner;

public class Author implements IInputAndShow {
    private int authorId;
    private String authorName;
    private String description;
    private int age;
    private boolean status; //0-Còn xuất bản, 1-Không còn xuất bản.

    public Author() {
    }

    public Author(int authorId, String authorName, String description, int age, boolean status) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.description = description;
        this.age = age;
        this.status = status;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner sc, List<Author> authors, List<Book> books) {
        this.authorId = inputAuthorId();
        this.authorName = inputAuthorName(sc);
        this.description = inputDescription(sc);
        this.age = inputAge(sc);
        this.status = inputStatus(sc);
    }

    @Override
    public void displayData(){
        System.out.printf("%-10s %-20s %-20s %-10s %-15s%n\n", getAuthorId(), getAuthorName(), getDescription(), getAge(), getStatus()? "Còn xuất bản" : "Không còn xuất bản");
    }

    //input authorid
    public int inputAuthorId() {
        int maxId = 0;
        for (Author author : AuthorFeatureImpl.authorList) {
            if (maxId < author.getAuthorId()) {
                maxId = author.getAuthorId();
            }
        }
        return maxId + 1;
    }

    public String inputAuthorName(Scanner sc) {
        System.out.println("Nhập tên tác giả: ");
        do {
            String authorName = sc.nextLine();
            if(!authorName.isEmpty()){
                return authorName;
            }else {
                System.err.println("Tên tác giả vừa nhập bị lỗi. Mời nhập lại (ko được để trống).");
            }
        }while(true);
    }

    public String inputDescription(Scanner sc) {
        System.out.println("Nhập mô tả về tác giả(Có thể để trống): ");
        String auhorDescription = sc.nextLine();
        if(auhorDescription.isEmpty()){
            return "Trống.";
        }else {
            return auhorDescription;
        }
    }

    public int inputAge(Scanner sc) {
        System.out.println("Nhập tuổi của tác giả: ");
        do{
            String age = sc.nextLine();
            try {
                if(Integer.parseInt(age)>0){
                    return Integer.parseInt(age);
                }else {
                    System.err.println("Tuổi vừa được nhập bị lỗi. Hãy nhập chữ số > 0. Mời nhập lại");
                }
            } catch (NumberFormatException e) {
                System.err.println("Tuổi vừa được nhập bị lỗi. Hãy nhập chữ số > 0. Mời nhập lại");
            }
        }while(true);
    }

    public boolean inputStatus(Scanner sc) {
        System.out.print("Nhập trạng thái của tác giả: ");
        do {
            String authorStatus = sc.nextLine();
            if(authorStatus.equalsIgnoreCase("true") || authorStatus.equalsIgnoreCase("false")){
                return Boolean.parseBoolean(authorStatus);
            }else {
                System.err.println("Invalid Singer Gender (true/false). Please try again.");
            }
        }while (true);
    }
}
