package domain.value.matchResult.impl;

import domain.value.matchResult.base.CountMatchResult;
import domain.value.matchResult.base.MatchEntry;
import domain.value.matchResult.base.MatchResult;

import java.util.List;

public class ModuleMatchResult extends CountMatchResult implements MatchResult {
    private String moduleName;

    public ModuleMatchResult(String moduleName, boolean matched, List<MatchEntry> matchEntries,
                             int matchCount, int totalCount, double matchProgress) {
        super(matched, matchEntries, matchCount, totalCount, matchProgress);
        this.moduleName = moduleName;
    }

    @Override
    public String getRequirementName() {
        return "模块课 " + moduleName;
    }
}
