package domain.entity.requirement.impl;

import domain.entity.LearningRecord;
import domain.entity.match.matchResult.base.CompulsoryMatchResult;
import domain.entity.requirement.base.*;
import domain.entity.match.matchResult.impl.MajorCompulsoryMatchResult;

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
