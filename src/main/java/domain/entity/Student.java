package domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Student extends AbstractEntity  {
    private final String id;
    private final String name;
    private final List<LearningRecord> learningRecordList;
    private Major major;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.learningRecordList = new ArrayList<>();
    }

    public Student(String id, String name, Major major) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.learningRecordList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<LearningRecord> getLearningRecordList() {
        return learningRecordList;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public void addLearningRecord(LearningRecord learningRecord) {
        this.learningRecordList.add(learningRecord);
    }

    @Override
    public String toString() {
        String res = "Student {\n" +
                "\tid=" + id + '\n' +
                "\tname=" + name + '\n' +
                "\tmajor=" + major.getName() + '\n';

        res += "\tlearning={\n";

        int lineRecord = 4;
        int currentRecord = 0;
        for (LearningRecord learningRecord : learningRecordList) {
            if (currentRecord == 0) res += "\t\t";
            res += learningRecord.getCourseId() + " ";

            currentRecord++;
            if (currentRecord == lineRecord) {
                res += '\n';
                currentRecord = 0;
            }
        }
        if (learningRecordList.size() % lineRecord != 0) res += '\n';

        res += "\t}\n";
        res += '}';
        return res;
    }
}
