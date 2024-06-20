package classAndStudent.entity;

import classAndStudent.feature.impl.ClassesFeatureImpl;
import classAndStudent.feature.impl.StudentFeatureImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Student implements IOData {
    private String studentId;
    private String studentName;
    private Date dateOfBirth;
    private Classes classes;
    private boolean sex;
    private boolean status;

    public Student() {
    }

    public Student(String studentId, String studentName, Date dateOfBirth, Classes classes, boolean sex, boolean status) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.dateOfBirth = dateOfBirth;
        this.classes = classes;
        this.sex = sex;
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public void inputData(Scanner sc) {
        this.studentId = inputStudentId(sc);
        this.studentName = inputStudentName(sc);
        this.dateOfBirth = inputDateOfBirth(sc);
        this.classes = inputClassData(sc);
        this.sex = inputSex(sc);
        this.status = inputStatus(sc);
    }

    @Override
    public void displayData() {
        System.out.printf("%-10s %-20s %-20s %-10s %-10s %-10s%n\n",
                getStudentId(), getStudentName(), getDateOfBirth(), getClasses().getClassName(), isSex() ? "Male" : "Female", isStatus() ? "Alive" : "Dead");
    }

    public String inputStudentId(Scanner sc) {
        System.out.println("Enter Student ID: ");
        do {
            String studentId = sc.nextLine();
            String regex = "S\\d{4}";
            if (studentId.matches(regex)) {
                boolean isExits = false;
                for (Student student : StudentFeatureImpl.studentList) {
                    if (student.getStudentId().equals(studentId)) {
                        isExits = true;
                    }
                }
                if (!isExits) {
                    return studentId;
                } else {
                    System.err.println("Student ID (Sxxxx) has been duplicate. Please try again.");
                }

            } else {
                System.err.println("Invalid Student ID (Sxxxx)");
            }
        } while (true);
    }

    public String inputStudentName(Scanner sc) {
        System.out.println("Enter Student Name: ");
        do {
            String studentName = sc.nextLine();
            if (!studentName.isEmpty()) {
                return studentName;
            } else {
                System.err.println("Invalid Student Name. Please try again.");
            }
        } while (true);
    }

    public Date inputDateOfBirth(Scanner sc) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Ensures strict parsing
        Date dateOfBirth = null;
        while (dateOfBirth == null) {
            System.out.println("Enter Student Date of Birth (dd/MM/yyyy): ");
            String dateInput = sc.nextLine();
            try {
                dateOfBirth = dateFormat.parse(dateInput);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
            }
        }

        return dateOfBirth;

    }

    public Classes inputClassData(Scanner sc) {
        System.out.println("Enter Class ID to add in: :");
        int count = 2;
        do {
            String classId = sc.nextLine();
            int index = -1;
            for (int i = 0; i < ClassesFeatureImpl.classesList.size(); i++) {
                if (ClassesFeatureImpl.classesList.get(i).getClassId().equals(classId)) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                return ClassesFeatureImpl.classesList.get(index);
            } else {
                if (count != 0) {
                    System.err.printf("Can't find Class ID. You have %d more tries.\n", count);
                    count--;
                    System.out.print("Please re-enter Class ID: ");
                } else {
                    System.out.println("You've exceeded the maximum number of tries.");
                    inputData(sc);
                }
            }
        } while (true);
    }

    public boolean inputSex(Scanner sc) {
        System.out.print("Enter Student Sex: ");
        do {
            String studentSex = sc.nextLine();
            if (studentSex.equalsIgnoreCase("true") || studentSex.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(studentSex);
            } else {
                System.err.println("Invalid Student Sex (true/false). Please try again.");
            }
        } while (true);
    }

    public boolean inputStatus(Scanner sc) {
        System.out.print("Enter Student Status: ");
        do {
            String studentStatus = sc.nextLine();
            if (studentStatus.equalsIgnoreCase("true") || studentStatus.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(studentStatus);
            } else {
                System.err.println("Invalid Student Status (true/false). Please try again.");
            }
        } while (true);
    }
}
