package classAndStudent.run;

import authorAndBook.entity.Author;
import authorAndBook.feature.impl.AuthorFeatureImpl;
import classAndStudent.entity.Classes;
import classAndStudent.entity.Student;
import classAndStudent.feature.IClasses;
import classAndStudent.feature.impl.ClassesFeatureImpl;
import classAndStudent.feature.impl.StudentFeatureImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ClassManagement {
    private static final IClasses classFeature = new ClassesFeatureImpl();
    public static void menuClass(Scanner sc) {
        boolean isLoop = true;
        do {
            System.out.println("➢ ===== MENU CLASS =====");
            System.out.println("1. Show list Class");
            System.out.println("2. Add new Class");
            System.out.println("3. Update Class by Id");
            System.out.println("4. Search Class by date between a-b");
            System.out.println("5. Statistical all male and female in Class");
            System.out.println("6. Delete Class");
            System.out.println("7. Exit");
            System.out.println("Your choice: ");
            int choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1:
                    handleShowAllClass();
                    break;
                case 2:
                    handleAddNewClass(sc);
                    break;
                case 3:
                    handleUpdateClassById(sc);
                    break;
                case 4:
                    handleSearchClassByDate(sc);
                    break;
                case 5:
                    handleStatisticalMandF();
                    break;
                case 6:
                    handleRemoveClassById(sc);
                    break;
                case 7:
                    isLoop =false;
                    break;
                default:
                    System.out.println("Bạn phải nhập vào 1 tới 7");
            }
        } while (isLoop);
    }

    //function
    public static void handleShowAllClass() {
        if (classFeature.findAll().isEmpty()){
            System.err.println("There are no classes in the database");
            return;
        }
        for (Classes classes : classFeature.findAll()) {
            classes.displayData();
        }
    }
    public static void handleAddNewClass(Scanner sc) {
        System.out.println("Enter number Class to add: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            Classes author = new Classes();
            author.inputData(sc);
            classFeature.addOrUpdate(author);
        }
    }
    public static void handleUpdateClassById(Scanner sc) {
        System.out.println("Enter Class Id to update: ");
        String idUpdate = sc.nextLine();
        int indexUpdate = classFeature.findIndexById(idUpdate);
        if (indexUpdate < 0) {
            System.out.println("There are no Class with this id exist");
            return;
        }
        boolean isLoop = true;
        do {
            Classes classUpdate = ClassesFeatureImpl.classesList.get(indexUpdate);
            System.out.println("1. Update Class Name.");
            System.out.println("2. Update Date");
            System.out.println("3. Update Class Status");
            System.out.println("4. Thoát");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:

                    classUpdate.setClassName(classUpdate.inputClassName(sc));
                    break;
                case 2:

                    classUpdate.setCreated(new Date());
                    break;
                case 3:

                    classUpdate.setStatus(classUpdate.inputStatus(sc));
                    break;
                case 4:
                    isLoop = false;
                    break;
                default:
                    System.err.println("Nhập từ 1-4");
            }
            classFeature.addOrUpdate(ClassesFeatureImpl.classesList.get(indexUpdate));
        } while (isLoop);
    }

    //searchClassByDate
    public static void handleSearchClassByDate(Scanner sc) {
        Date firstDate = inputDate(sc);
        Date lastDate = inputDate(sc);
        int count=0;
        for (Classes classes : ClassesFeatureImpl.classesList) {
            if (classes.getCreated().before(lastDate) && classes.getCreated().after(firstDate)) {
                classes.displayData();
                count++;
            }
        }
        System.out.println("There are " + count + " classes between " + firstDate + " and " + lastDate);
    }
    //support search Class by Date
    public static Date inputDate(Scanner sc) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        Date dateOfBirth = null;

        while (dateOfBirth == null) {
            System.out.print("Enter Date (dd/MM/yyyy): ");
            String dateInput = sc.nextLine();

            try {
                dateOfBirth = dateFormat.parse(dateInput);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
            }
        }

        return dateOfBirth;
    }
    public static void handleStatisticalMandF() {
        for (Classes classes : classFeature.findAll()) {
            int countMale = 0;
            int countFemale = 0;
            for (Student student : StudentFeatureImpl.studentList){
                //true ->Male // false->Female
                if (student.getClasses().getClassId().equals(classes.getClassId())) {
                    if (student.isSex()){
                        countMale++;
                    }else {
                        countFemale++;
                    }
                }
            }
            classes.displayData();
            System.out.println("Male: " + countMale + " Female: " + countFemale);
        }
    }
    public static void handleRemoveClassById(Scanner sc) {
        System.out.println("Enter Class Id to remove: ");
        String idDelete = sc.nextLine();
        classFeature.deleteById(idDelete);
    }
}
