package hackathontest.advance.b1.bussinessImp;

import hackathontest.advance.b1.bussiness.IShop;

import java.util.Scanner;

public class Author implements IShop {
    private int authorId;
    private String authorName;
    private Boolean sex;
    private int year; //year of birth

    public Author() {
    }

    public Author(int authorId, String authorName, Boolean sex, int year) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.sex = sex;
        this.year = year;
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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        this.authorId = inputAuthorId(sc);
        this.authorName = inputAuthorName(sc);
        this.sex = inputAuthorSex(sc);
        this.year = inputYear(sc);
    }

    @Override
    public void displayData() {
        System.out.printf("%-10s %-20s %-10s %-10s%n\n",this.authorId,this.authorName,this.sex,this.year);
    }

    public int inputAuthorId(Scanner sc){
        System.out.println("Enter Author ID: ");
        do {
            String authorId = sc.nextLine();
            try {
                if (Integer.parseInt(authorId) >= 0) {
                    return Integer.parseInt(authorId);
                } else {
                    System.err.println("Invalid author Id value. Must enter number > 0. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid author Id value. Try again.");
            }
        }while (true);
    }

    public String inputAuthorName(Scanner sc){
        System.out.println("Enter Author Name: ");
        return sc.nextLine();
    }

    public Boolean inputAuthorSex(Scanner sc){
        System.out.println("Enter Author sex: ");
        do {
            String sex = sc.nextLine();
            if (sex.equalsIgnoreCase("true") || sex.equalsIgnoreCase("false")){
                return Boolean.parseBoolean(sex);
            }else {
                System.err.println("Invalid sex input (true/false). Try again.");
            }
        }while (true);
    }

    public int inputYear(Scanner sc){
        System.out.println("Enter Year: ");
        do {
            String authorYear = sc.nextLine();
            try {
                if (Integer.parseInt(authorYear) >= 0) {
                    return Integer.parseInt(authorYear);
                } else {
                    System.err.println("Invalid Year value. Must enter number > 0. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid Year value. Try again.");
            }
        }while (true);
    }
}
