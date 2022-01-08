package domain.value.requirement.impl;

import domain.entity.LearningRecord;
import domain.value.matchResult.base.CountMatchResult;
import domain.value.requirement.base.*;
import domain.value.matchResult.impl.ModuleMatchResult;

import java.util.List;
import java.util.Map;


/**
 * 模块选修要求
 * 继承【修读门数要求】
 */
public class ModuleRequirement extends CountRequirement implements Requirement {
    private String moduleName;

    public ModuleRequirement(String moduleName, List<String> courseIds, int totalCount) {
        super(courseIds, totalCount);
        this.moduleName = moduleName;
    }

    @Override
    public ModuleMatchResult matchRequirement(final List<LearningRecord> learningRecordList,
                                              final List<Requirement> relatedRequirements,
                                              final Map<String, String> courseExchangeMap) {
        CountMatchResult matchResult = super.matchRequirement(learningRecordList,
                relatedRequirements, courseExchangeMap);
        return new ModuleMatchResult(moduleName, matchResult.isMatched(),
                matchResult.getMatchEntries(), matchResult.getMatchCount(),
                matchResult.getTotalCount(), matchResult.getMatchProgress());
    }
}