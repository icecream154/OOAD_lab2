package domain.value.requirement.impl;

import domain.entity.Course;
import domain.entity.LearningRecord;
import domain.factory.CourseFactory;
import domain.value.matchResult.base.CountMatchResult;
import domain.value.matchResult.base.MatchEntry;
import domain.value.requirement.base.CompulsoryRequirement;
import domain.value.requirement.base.CountRequirement;
import domain.value.requirement.base.CreditRequirement;
import domain.value.requirement.base.Requirement;
import domain.value.matchResult.base.MatchResult;
import domain.value.matchResult.impl.OtherElectiveMatchResult;

import java.util.*;

public class OtherElectiveRequirement implements Requirement {
    private int totalCredit;

    public OtherElectiveRequirement(int totalCredit) {
        this.totalCredit = totalCredit;
    }

    private void addMatchedCourseIds(List<String> matchedCourse, List<MatchEntry> matchEntries) {
        for (MatchEntry matchEntry : matchEntries) {
            if (!matchEntry.isExchange()) {
                matchedCourse.add(matchEntry.getRequireCourse().getId());
            } else {
                matchedCourse.add(matchEntry.getExchangeCourse().getId());
            }
        }
    }

    @Override
    public MatchResult matchRequirement(List<LearningRecord> learningRecordList,
                                        List<Requirement> relatedRequirements,
                                        Map<String, String> courseExchangeMap) {
        List<String> unMatchedCourseIds = new ArrayList<>();
        for (LearningRecord learningRecord : learningRecordList) {
            unMatchedCourseIds.add(learningRecord.getCourseId());
        }

        List<String> matchedCourseIds = new ArrayList<>();
        Map<MatchResult, Integer> requirementExtraCredit = new HashMap<>();

        // 总任意选修学分
        int matchCredit = 0;

        for (Requirement requirement : relatedRequirements) {
            if (requirement instanceof CreditRequirement || requirement instanceof CompulsoryRequirement) {
                MatchResult matchResult = requirement.matchRequirement(
                        learningRecordList, relatedRequirements, courseExchangeMap
                );

                // 记录用于匹配的课程
                addMatchedCourseIds(matchedCourseIds, matchResult.getMatchEntries());
                // 记录多出的学分
                int more = matchResult.getMatchNumber() - matchResult.getRequireNumber();
                more = Math.max(more, 0);
                matchCredit += more;
                requirementExtraCredit.put(matchResult, more);

                continue;
            }

            if (requirement instanceof CountRequirement) {
                CountMatchResult matchResult = ((CountRequirement) requirement).matchRequirement(
                        learningRecordList, relatedRequirements, courseExchangeMap
                );
                // 记录用于匹配的课程
                addMatchedCourseIds(matchedCourseIds, matchResult.getMatchEntries());
                // 记录多出的学分
                int more = matchResult.getExtraCredit();
                matchCredit += more;
                requirementExtraCredit.put(matchResult, more);
            }
        }

        unMatchedCourseIds.removeAll(matchedCourseIds);
        Collection<Course> unmatchedCourse = CourseFactory.getCourseMapFromIds(unMatchedCourseIds).values();

        // 构造剩余匹配的课程列表
        List<MatchEntry> matchEntries = new ArrayList<>();
        for (Course course :unmatchedCourse) {
            matchCredit += course.getCredit();
            matchEntries.add(new MatchEntry(course, false, null));
        }

        // 生成匹配结果
        double matchProgress;
        if (totalCredit == 0 ) {
            matchProgress = 1;
        } else {
            matchProgress = (double) matchCredit / totalCredit;
        }
        if (matchProgress > 1) matchProgress = 1;

        return new OtherElectiveMatchResult(matchCredit >= totalCredit, matchEntries,
                matchCredit, totalCredit, matchProgress, requirementExtraCredit);
    }
}
