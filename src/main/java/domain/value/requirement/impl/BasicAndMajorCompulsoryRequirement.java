package domain.value.requirement.impl;

import domain.entity.LearningRecord;
import domain.value.matchResult.base.CompulsoryMatchResult;
import domain.value.requirement.base.CompulsoryRequirement;
import domain.value.requirement.base.Requirement;
import domain.value.matchResult.impl.BasicAndMajorCompulsoryMatchResult;

import java.util.List;
import java.util.Map;

/**
 * 基础必修要求
 * 继承【必修要求】
 */
public class BasicAndMajorCompulsoryRequirement extends CompulsoryRequirement implements Requirement {

    public BasicAndMajorCompulsoryRequirement(List<String> courseIds) {
        super(courseIds);
    }

    @Override
    public BasicAndMajorCompulsoryMatchResult matchRequirement(final List<LearningRecord> learningRecordList,
                                              final List<Requirement> relatedRequirements,
                                              final Map<String, String> courseExchangeMap) {
        CompulsoryMatchResult matchResult = super.matchRequirement(learningRecordList,
                relatedRequirements, courseExchangeMap);
        return new BasicAndMajorCompulsoryMatchResult(matchResult.isMatched(),
                matchResult.getMatchEntries(), matchResult.getMatchCourseNum(), matchResult.getMatchCredit(),
                matchResult.getTotalCourseNum(),
                matchResult.getTotalCourseCredit(), matchResult.getMatchProgress());
    }
}
