package classAndStudent.feature.impl;

import classAndStudent.entity.Classes;
import classAndStudent.entity.Student;
import classAndStudent.feature.IBaseFeature;
import classAndStudent.feature.IClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ClassesFeatureImpl implements IClasses {
    public static List<Classes> classesList = new ArrayList<>();

    @Override
    public List<Classes> findAll() {
        return classesList;
    }

    @Override
    public void addOrUpdate(Classes classes) {
        int indexCheck = findIndexById(classes.getClassId());
        if (indexCheck < 0) {
            //cant find id -> add
            classesList.add(classes);
        } else {
            //update
            classesList.set(indexCheck, classes);
        }
    }

    @Override
    public void deleteById(String id) {
        int indexDelete = findIndexById(id);
        if (indexDelete >= 0) {
            boolean isExist = false;
            for (Student student : StudentFeatureImpl.studentList) {
                if (student.getClasses().getClassId().equals(id)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                classesList.remove(indexDelete);
            } else {
                classesList.get(indexDelete).setStatus((byte) 4);
            }
        } else {
            System.err.println("There is no such class with id");
        }
    }

    @Override
    public int findIndexById(String id) {
        for (int i = 0; i < classesList.size(); i++) {
            if (classesList.get(i).getClassId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

}
