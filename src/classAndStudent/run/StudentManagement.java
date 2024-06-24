package classAndStudent.run;


import classAndStudent.entity.Classes;
import classAndStudent.entity.Student;
import classAndStudent.feature.IStudent;
import classAndStudent.feature.impl.ClassesFeatureImpl;
import classAndStudent.feature.impl.StudentFeatureImpl;

import java.util.Date;
import java.util.Scanner;

public class StudentManagement {
    private static final IStudent studentFeature = new StudentFeatureImpl();

    public static void menuStudent(Scanner sc) {
        boolean isLoop = true;
        do {
            System.out.println("➢ ===== MENU STUDENT =====");
            System.out.println("1. Show list Student");
            System.out.println("2. Add new Student");
            System.out.println("3. Update Student by Id");
            System.out.println("4. Search Student by Name");
            System.out.println("5. Show all Student by Class");
            System.out.println("6. Delete Student");
            System.out.println("7. Exit");
            System.out.println("Your choice: ");
            int choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1:
                    handleShowAllStudent();
                    break;
                case 2:
                    handleAddNewStudent(sc);
                    break;
                case 3:
                    handleUpdateStudentById(sc);
                    break;
                case 4:
                    handleSearchStudentByName(sc);
                    break;
                case 5:
                    handleStatisticalClass(sc);
                    break;
                case 6:
                    handleRemoveStudentById(sc);
                    break;
                case 7:
                    isLoop = false;
                    break;
                default:
                    System.out.println("Bạn phải nhập vào 1 tới 7");
            }
        } while (isLoop);
    }

    public static void handleShowAllStudent() {
        if (studentFeature.findAll().isEmpty()) {
            System.err.println("There are no students in the database");
            return;
        }
        for (Student student : studentFeature.findAll()) {
            student.displayData();
        }
    }

    public static void handleAddNewStudent(Scanner sc) {
        System.out.println("Enter number Student to add: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            Student student = new Student();
            student.inputData(sc);
            studentFeature.addOrUpdate(student);
        }
    }

    public static void handleUpdateStudentById(Scanner sc) {
        System.out.println("Enter Student Id to update: ");
        String idUpdate = sc.nextLine();
        int indexUpdate = studentFeature.findIndexById(idUpdate);
        if (indexUpdate < 0) {
            System.out.println("There are no Class with this id exist");
            return;
        }
        boolean isLoop = true;
        do {
            Student studentUpdate = StudentFeatureImpl.studentList.get(indexUpdate);
            System.out.println("1. Update Student Name.");
            System.out.println("2. Update Student Date of Birth");
            System.out.println("3. Update Student Class.");
            System.out.println("4. Update Student Sex.");
            System.out.println("5. Update Student Status.");
            System.out.println("6. Thoát");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:

                    studentUpdate.setStudentName(studentUpdate.inputStudentName(sc));
                    break;
                case 2:

                    studentUpdate.setDateOfBirth(studentUpdate.inputDateOfBirth(sc));
                    break;
                case 3:

                    studentUpdate.setClasses(studentUpdate.inputClassData(sc));
                    break;
                case 4:

                    studentUpdate.setSex(studentUpdate.inputSex(sc));
                    break;
                case 5:

                    studentUpdate.setStatus(studentUpdate.inputStatus(sc));
                    break;
                case 6:
                    isLoop = false;
                    break;
                default:
                    System.err.println("Nhập từ 1-6");
            }
            studentFeature.addOrUpdate(StudentFeatureImpl.studentList.get(indexUpdate));
        } while (isLoop);
    }

    public static void handleSearchStudentByName(Scanner sc) {
        System.out.println("Enter Student Name to Search: ");
        String studentName = sc.nextLine();
        int count = 0;
        for (Student student : studentFeature.findAll()) {
            if (student.getStudentName().equals(studentName)) {
                student.displayData();
                count++;
            }
        }
        System.out.println("There are " + count + " students with that name");
    }

    public static void handleStatisticalClass(Scanner sc) {
        System.out.println("Enter Student Class Id: ");
        String classId = sc.nextLine();
        for (Student student : studentFeature.findAll()) {
            if(student.getClasses().getClassId().equals(classId)){
                student.displayData();
            }
        }
        System.out.println("This is the Statistic Class: " + classId);
    }

    public static void handleRemoveStudentById(Scanner sc) {
        System.out.println("Enter Student Id to remove: ");
        String idDelete = sc.nextLine();
        studentFeature.deleteById(idDelete);
    }
}
