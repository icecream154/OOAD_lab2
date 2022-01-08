package domain.factory;

import domain.entity.Course;
import domain.entity.LearningRecord;
import domain.entity.Student;

public class LearningRecordFactory {

    private static int nextId = 0;

    public static LearningRecord registerLearningRecord(String studentId, String courseId) {
        int currentRecordId = nextId;
        nextId++;
        LearningRecord learningRecord = new LearningRecord(currentRecordId, studentId, courseId);

        Student student = StudentFactory.getStudentById(studentId);
        Course course = CourseFactory.getCourseById(courseId);
        if (student == null || course == null) return null;

        student.addLearningRecord(learningRecord);
        if (!StudentFactory.save(student)) return null;

        return learningRecord;
    }
}
