package domain.factory;

import domain.entity.Student;
import domain.entity.Major;

import java.util.HashMap;
import java.util.Map;

public class StudentFactory {

    private static final Map<String, Student> studentMap = new HashMap<>();

    public static Student registerStudent(String studentId, String studentName) {
        if (studentMap.get(studentId) != null) return null;

        Student student = new Student(studentId, studentName);
        studentMap.put(studentId, student);
        return student;
    }

    public static Student registerStudent(String studentId, String studentName, Major major) {
        if (studentMap.get(studentId) != null) return null;

        Student student = new Student(studentId, studentName, major);
        studentMap.put(studentId, student);
        return student;
    }

    public static Student getStudentById(String studentId) {
        return studentMap.get(studentId);
    }

    // 存储在内存中，不需要额外操作
    public static boolean save(Student student) {
        return true;
    }
}
