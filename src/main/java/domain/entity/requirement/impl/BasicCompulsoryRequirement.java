package domain.entity.requirement.impl;

import domain.entity.LearningRecord;
import domain.entity.match.matchResult.base.CompulsoryMatchResult;
import domain.entity.requirement.base.*;
import domain.entity.match.matchResult.impl.BasicCompulsoryMatchResult;

import java.util.*;

/**
 * 基础必修要求
 * 继承【必修要求】
 */
public class BasicCompulsoryRequirement extends CompulsoryRequirement implements Requirement {

    public BasicCompulsoryRequirement(List<String> courseIds) {
        super(courseIds);
    }

    @Override
    public BasicCompulsoryMatchResult matchRequirement(final List<LearningRecord> learningRecordList,
                                              final List<Requirement> relatedRequirements,
                                              final Map<String, String> courseExchangeMap) {
        CompulsoryMatchResult matchResult = super.matchRequirement(learningRecordList,
                relatedRequirements, courseExchangeMap);
        return new BasicCompulsoryMatchResult(matchResult.isMatched(),
                matchResult.getMatchEntries(), matchResult.getMatchCourseNum(), matchResult.getMatchCredit(),
                matchResult.getTotalCourseNum(),
                matchResult.getTotalCourseCredit(), matchResult.getMatchProgress());
    }
}
