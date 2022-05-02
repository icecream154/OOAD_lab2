package domain.entity.match.matchResult.base;

import java.util.List;

public class CreditMatchResult implements MatchResult {
    private final boolean matched;
    private final List<MatchEntry> matchEntries;
    private final int matchCredit;
    private final int totalCredit;
    private final double matchProgress;

    public CreditMatchResult(boolean matched, List<MatchEntry> matchEntries,
                             int matchCredit, int totalCredit, double matchProgress) {
        this.matched = matched;
        this.matchEntries = matchEntries;
        this.matchCredit = matchCredit;
        this.totalCredit = totalCredit;
        this.matchProgress = matchProgress;
    }

    public int getMatchCredit() {
        return matchCredit;
    }

    public int getTotalCredit() {
        return totalCredit;
    }

    @Override
    public boolean isMatched() {
        return matched;
    }

    @Override
    public String getRequirementName() {
        return null;
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

}
