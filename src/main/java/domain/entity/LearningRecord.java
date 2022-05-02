package domain.entity;

import java.util.ArrayList;
import java.util.List;

public class LearningRecord extends AbstractEntity {
    private int id;
    private String studentId;
    private String courseId;

    public static List<String> getCourseIdList(final List<LearningRecord> learningRecordList) {
        List<String> res = new ArrayList<>();
        for (LearningRecord learningRecord : learningRecordList) {
            res.add(learningRecord.getCourseId());
        }
        return res;
    }

    public LearningRecord(int id, String studentId, String courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Integer getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return "LearningRecord{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}
