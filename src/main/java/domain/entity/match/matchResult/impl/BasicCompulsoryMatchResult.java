package domain.entity.match.matchResult.impl;

import domain.entity.match.matchResult.base.CompulsoryMatchResult;
import domain.entity.match.matchResult.base.MatchEntry;
import domain.entity.match.matchResult.base.MatchResult;

import java.util.List;

public class BasicCompulsoryMatchResult extends CompulsoryMatchResult implements MatchResult {

    public BasicCompulsoryMatchResult(boolean matched, List<MatchEntry> matchEntries,
                                      int matchCourseNum, int matchCredit, int totalCourseNum,
                                      int totalCourseCredit, double matchProgress) {
        super(matched, matchEntries, matchCourseNum, matchCredit, totalCourseNum, totalCourseCredit, matchProgress);
    }

    @Override
    public String getRequirementName() {
        return "必修的基础课";
    }

}
