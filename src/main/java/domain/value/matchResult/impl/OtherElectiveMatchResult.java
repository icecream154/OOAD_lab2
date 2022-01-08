package domain.value.matchResult.impl;

import domain.value.matchResult.base.MatchEntry;
import domain.value.matchResult.base.MatchResult;
import domain.value.requirement.base.Requirement;

import java.util.List;
import java.util.Map;

public class OtherElectiveMatchResult implements MatchResult {
    private final boolean matched;
    private final List<MatchEntry> matchEntries;
    private final int matchCredit;
    private final int totalCredit;
    private final double matchProgress;
    private final Map<MatchResult, Integer> requirementExtraCredit;

    public OtherElectiveMatchResult(boolean matched, List<MatchEntry> matchEntries,
                                    int matchCredit, int totalCredit, double matchProgress,
                                    Map<MatchResult, Integer> requirementExtraCredit) {
        this.matched = matched;
        this.matchEntries = matchEntries;
        this.matchCredit = matchCredit;
        this.totalCredit = totalCredit;
        this.matchProgress = matchProgress;
        this.requirementExtraCredit = requirementExtraCredit;
    }

    public int getMatchCredit() {
        return matchCredit;
    }

    public int getTotalCredit() {
        return totalCredit;
    }

    public Map<MatchResult, Integer> getRequirementExtraCredit() {
        return requirementExtraCredit;
    }

    @Override
    public boolean isMatched() {
        return matched;
    }

    @Override
    public double getMatchProgress() {
        return matchProgress;
    }

    @Override
    public int getMatchNumber() {
        return getMatchCredit();
    }

    @Override
    public int getRequireNumber() {
        return getTotalCredit();
    }

    @Override
    public List<MatchEntry> getMatchEntries() {
        return matchEntries;
    }

    @Override
    public String getRequirementName() {
        return "任意选修课";
    }
}
