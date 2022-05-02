package domain.entity.match.matchResult.impl;

import domain.entity.match.matchResult.base.CountMatchResult;
import domain.entity.match.matchResult.base.MatchEntry;
import domain.entity.match.matchResult.base.MatchResult;

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
