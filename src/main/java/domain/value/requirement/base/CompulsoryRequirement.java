package domain.value.requirement.base;

import domain.entity.Course;
import domain.entity.LearningRecord;
import domain.factory.CourseFactory;
import domain.value.matchResult.base.CompulsoryMatchResult;
import domain.value.matchResult.base.MatchEntry;

import java.util.*;

public abstract class CompulsoryRequirement extends AbstractRequirement implements Requirement {
    private final List<String> courseIds;

    public CompulsoryRequirement(List<String> courseIds) {
        this.courseIds = courseIds;
    }

    @Override
    public CompulsoryMatchResult matchRequirement(final List<LearningRecord> learningRecordList,
                                                  final List<Requirement> relatedRequirements,
                                                  final Map<String, String> courseExchangeMap) {
        // 待匹配的课程
        Map<String, Course> courseMap = CourseFactory.getCourseMapFromIds(courseIds);
        // 匹配结果
        List<MatchEntry> matchEntries = getMatchedEntries(new HashMap<>(courseMap),
                learningRecordList, courseExchangeMap);
        // 计算成功匹配的学分
        int matchCredit = 0;
        for (MatchEntry matchEntry : matchEntries) {
            matchCredit += matchEntry.getRequireCourse().getCredit();
        }
        // 计算所需总学分
        int totalCredit = 0;
        for (Course course : courseMap.values()) {
            totalCredit += course.getCredit();
        }

        double matchProgress;
        if (totalCredit == 0) {
            matchProgress = 1;
        } else {
            matchProgress = (double) matchCredit / totalCredit;
        }

        return new CompulsoryMatchResult(matchCredit == totalCredit, matchEntries,
                matchEntries.size(), matchCredit, courseIds.size(),
                totalCredit, matchProgress);
    }
}