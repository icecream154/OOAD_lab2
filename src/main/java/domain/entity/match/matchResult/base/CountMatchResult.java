package domain.entity.match.matchResult.base;

import java.util.List;

public class CountMatchResult implements MatchResult {
    private final boolean matched;
    private final List<MatchEntry> matchEntries;
    private final int matchCount;
    private final int totalCount;
    private final double matchProgress;

    public CountMatchResult(boolean matched, List<MatchEntry> matchEntries,
                            int matchCount, int totalCount, double matchProgress) {
        this.matched = matched;
        this.matchEntries = matchEntries;
        this.matchCount = matchCount;
        this.totalCount = totalCount;
        this.matchProgress = matchProgress;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getExtraCredit() {
        if (matchCount <= totalCount) return 0;

        int res = 0;
        int currentIndex = 0;
        for (MatchEntry matchEntry : matchEntries) {
            if (currentIndex < totalCount) {
                currentIndex++;
                continue;
            }
            res += matchEntry.getRequireCourse().getCredit();
        }
        return res;
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
        return getMatchCount();
    }

    @Override
    public int getRequireNumber() {
        return getTotalCount();
    }

    @Override
    public List<MatchEntry> getMatchEntries() {
        return matchEntries;
    }

}
