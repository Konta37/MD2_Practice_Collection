package classAndStudent.entity;

import classAndStudent.feature.impl.ClassesFeatureImpl;

import java.util.Date;
import java.util.Scanner;

public class Classes implements IOData {
    private String classId;
    private String className;
    private Date created;
    private byte status;

    public Classes() {}

    public Classes(String classId, String className, Date created, byte status) {
        this.classId = classId;
        this.className = className;
        this.created = created;
        this.status = status;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner sc) {
        this.classId = inputClassId(sc);
        this.className = inputClassName(sc);
        this.created = new Date();
        this.status = inputStatus(sc);
        System.out.println("===================");
    }

    @Override
    public void displayData() {
        String strStatus ="";
        switch (getStatus()) {
            case 0:
                strStatus = "Ready to learn";
                break;
            case 1:
                strStatus = "Still learn";

                break;
            case 2:
                strStatus = "Learn OJT";

                break;
            case 3:
                strStatus = "Graduate";

                break;
            case 4:
                strStatus = "Stop learning";
                break;
        }
        System.out.printf("%-10s %-20s %-20s %-20s%n\n",
                getClassId(), getClassName(), getCreated(), strStatus);
    }

    //validate
    public String inputClassId(Scanner sc) {
        System.out.println("Enter class ID: ");
        do {
            String classId = sc.nextLine();
            String regex = "C\\d{3}";
            if (classId.matches(regex)) {
                boolean isCheck = false;
                for (int i = 0; i < ClassesFeatureImpl.classesList.size(); i++) {
                    if (ClassesFeatureImpl.classesList.get(i).getClassId().equals(classId)) {
                        isCheck = true;
                        break;
                    }
                }
                if (isCheck) {
                    System.err.println("Class ID (Cxxx) has been duplicate. Please try again.");
                } else {
                    return classId;
                }
            } else {
                System.err.println("Invalid Class ID (Cxxx)");
            }
        } while (true);
    }

    public String inputClassName(Scanner sc) {
        System.out.println("Enter class name: ");
        do {
            String className = sc.nextLine();
            if(!className.isEmpty()){
                boolean isExist = false;
                for (Classes c : ClassesFeatureImpl.classesList){
                    if(c.getClassName().equals(className)){
                        isExist = true;
                        break;
                    }
                }
                if(!isExist){
                    return className;
                }else {
                    System.err.println("Class name has been duplicate. Please try again.");
                }
            }else {
                System.err.println("Invalid Class Name. Please try again.");
            }
        }while(true);
    }

    public byte inputStatus(Scanner sc) {
        System.out.println("Enter status (0-4): ");
        do {
            String status = sc.nextLine();
            try {
                if (Byte.parseByte(status) >= 0 && Byte.parseByte(status) <= 4) {
                    return Byte.parseByte(status);
                } else {
                    System.err.println("Invalid status value. Must enter between 0-4. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid status value. Try again.");
            }
        }while (true);
    }
}
