package domain.value.matchResult.base;

import java.util.List;

public class CompulsoryMatchResult implements MatchResult {
    private final boolean matched;
    private final List<MatchEntry> matchEntries;
    private final int matchCourseNum;
    private final int matchCredit;
    private final int totalCourseNum;
    private final int totalCourseCredit;
    private final double matchProgress;

    public CompulsoryMatchResult(boolean matched, List<MatchEntry> matchEntries,
                                 int matchCourseNum, int matchCredit, int totalCourseNum,
                                 int totalCourseCredit, double matchProgress) {
        this.matched = matched;
        this.matchEntries = matchEntries;
        this.matchCourseNum = matchCourseNum;
        this.matchCredit = matchCredit;
        this.totalCourseNum = totalCourseNum;
        this.totalCourseCredit = totalCourseCredit;
        this.matchProgress = matchProgress;
    }

    public int getTotalCourseNum() {
        return totalCourseNum;
    }

    public int getTotalCourseCredit() {
        return totalCourseCredit;
    }

    public int getMatchCourseNum() {
        return matchCourseNum;
    }

    public int getMatchCredit() {
        return matchCredit;
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
        return getTotalCourseCredit();
    }

    @Override
    public List<MatchEntry> getMatchEntries() {
        return matchEntries;
    }

}
