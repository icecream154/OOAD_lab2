package domain.entity.requirement.base;

import domain.entity.LearningRecord;
import domain.entity.match.matchResult.base.MatchResult;

import java.util.List;
import java.util.Map;

public interface Requirement {
    MatchResult matchRequirement(final List<LearningRecord> learningRecordList,
                                 final List<Requirement> relatedRequirements,
                                 final Map<String, String> courseExchangeMap);
}
