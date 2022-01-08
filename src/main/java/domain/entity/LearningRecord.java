package domain.entity;

public class LearningRecord extends AbstractEntity {
    private int id;
    private String studentId;
    private String courseId;

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
