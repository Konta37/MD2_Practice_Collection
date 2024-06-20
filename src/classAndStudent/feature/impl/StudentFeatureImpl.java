package classAndStudent.feature.impl;

import classAndStudent.entity.Student;
import classAndStudent.feature.IStudent;

import java.util.ArrayList;
import java.util.List;

public class StudentFeatureImpl implements IStudent {
    public static List<Student> studentList = new ArrayList<Student>();
    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public void addOrUpdate(Student student) {
        int indexCheck = findIndexById(student.getStudentId());
        if(indexCheck <0){
            //cant find id -> add
            studentList.add(student);
        }else {
            //update
            studentList.set(indexCheck, student);
        }
    }

    @Override
    public void deleteById(String id) {
        int indexDelete = findIndexById(id);
        if (indexDelete >= 0) {
            studentList.remove(indexDelete);
        } else {
            System.err.println("There is no such student with id");
        }
    }

    @Override
    public int findIndexById(String id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
