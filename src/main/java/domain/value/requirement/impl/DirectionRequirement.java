package domain.value.requirement.impl;

import domain.entity.LearningRecord;
import domain.value.matchResult.base.CreditMatchResult;
import domain.value.requirement.base.CreditRequirement;
import domain.value.requirement.base.Requirement;
import domain.value.matchResult.impl.DirectionMatchResult;

import java.util.List;
import java.util.Map;

/**
 * 方向选修要求
 * 继承【学分要求】
 */
public class DirectionRequirement extends CreditRequirement implements Requirement {
    public String directionName;

    public DirectionRequirement(String directionName,
                                List<String> courseIds, int totalCredit) {
        super(courseIds, totalCredit);
        this.directionName = directionName;
    }

    @Override
    public DirectionMatchResult matchRequirement(final List<LearningRecord> learningRecordList,
                                                 final List<Requirement> relatedRequirements,
                                                 final Map<String, String> courseExchangeMap) {
        CreditMatchResult matchResult = super.matchRequirement(learningRecordList,
                relatedRequirements, courseExchangeMap);
        return new DirectionMatchResult(directionName, matchResult.isMatched(),
                matchResult.getMatchEntries(), matchResult.getMatchCredit(),
                matchResult.getTotalCredit(), matchResult.getMatchProgress());
    }
}