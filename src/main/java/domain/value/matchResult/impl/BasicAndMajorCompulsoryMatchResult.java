package domain.value.matchResult.impl;

import domain.value.matchResult.base.CompulsoryMatchResult;
import domain.value.matchResult.base.MatchEntry;
import domain.value.matchResult.base.MatchResult;

import java.util.List;

public class BasicAndMajorCompulsoryMatchResult extends CompulsoryMatchResult implements MatchResult {

    public BasicAndMajorCompulsoryMatchResult(boolean matched, List<MatchEntry> matchEntries,
                                              int matchCourseNum, int matchCredit, int totalCourseNum,
                                              int totalCourseCredit, double matchProgress) {
        super(matched, matchEntries, matchCourseNum, matchCredit, totalCourseNum, totalCourseCredit, matchProgress);
    }

    @Override
    public String getRequirementName() {
        return "必修的基础课与专业基础课";
    }

}
