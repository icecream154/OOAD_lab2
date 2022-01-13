package domain.value.requirement.base;

import domain.entity.Course;
import domain.entity.LearningRecord;
import domain.factory.CourseFactory;
import domain.value.matchResult.base.CountMatchResult;
import domain.value.matchResult.base.MatchEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 门数要求抽象基类
 */
public abstract class CountRequirement extends AbstractRequirement implements Requirement {
    private final List<String> courseIds;
    private final int totalCount;

    public CountRequirement(List<String> courseIds, int totalCount) {
        this.courseIds = courseIds;
        this.totalCount = totalCount;
    }

    @Override
    public CountMatchResult matchRequirement(final List<LearningRecord> learningRecordList,
                                             final List<Requirement> relatedRequirements,
                                             final Map<String, String> courseExchangeMap) {
        // 待匹配的课程
        Map<String, Course> courseMap = CourseFactory.getCourseMapFromIds(courseIds);
        // 匹配结果
        List<MatchEntry> matchEntries = getMatchedEntries(new HashMap<>(courseMap),
                learningRecordList, courseExchangeMap);

        // 生成匹配结果
        double matchProgress;
        if (totalCount == 0 ) {
            matchProgress = 1;
        } else {
            matchProgress = (double) matchEntries.size() / totalCount;
        }
        if (matchProgress > 1) matchProgress = 1;

        return new CountMatchResult(matchEntries.size() >= totalCount, matchEntries,
                matchEntries.size(), totalCount, matchProgress);
    }
}