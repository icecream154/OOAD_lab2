package domain.utils;

import domain.entity.LearningRecord;

import java.util.ArrayList;
import java.util.List;

public class LearningRecordUtil {
    public static List<String> getCourseIdList(final List<LearningRecord> learningRecordList) {
        List<String> res = new ArrayList<>();
        for (LearningRecord learningRecord : learningRecordList) {
            res.add(learningRecord.getCourseId());
        }
        return res;
    }

}
