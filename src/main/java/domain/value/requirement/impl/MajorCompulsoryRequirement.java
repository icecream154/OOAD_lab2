package domain.value.requirement.impl;

import domain.entity.LearningRecord;
import domain.value.matchResult.base.CompulsoryMatchResult;
import domain.value.requirement.base.*;
import domain.value.matchResult.impl.MajorCompulsoryMatchResult;

import java.util.*;

/**
 * 专业必修要求
 * 继承【必修要求】
 */
public class MajorCompulsoryRequirement extends CompulsoryRequirement implements Requirement {

    public MajorCompulsoryRequirement(List<String> courseIds) {
        super(courseIds);
    }

    @Override
    public MajorCompulsoryMatchResult matchRequirement(final List<LearningRecord> learningRecordList,
                                                       final List<Requirement> relatedRequirements,
                                                       final Map<String, String> courseExchangeMap) {
        CompulsoryMatchResult matchResult = super.matchRequirement(learningRecordList,
                relatedRequirements, courseExchangeMap);
        return new MajorCompulsoryMatchResult(matchResult.isMatched(),
                matchResult.getMatchEntries(), matchResult.getMatchCourseNum(), matchResult.getMatchCredit(),
                matchResult.getTotalCourseNum(),
                matchResult.getTotalCourseCredit(), matchResult.getMatchProgress());
    }
}
