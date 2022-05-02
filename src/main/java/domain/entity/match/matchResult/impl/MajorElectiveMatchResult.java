package domain.entity.match.matchResult.impl;

import domain.entity.match.matchResult.base.CreditMatchResult;
import domain.entity.match.matchResult.base.MatchEntry;
import domain.entity.match.matchResult.base.MatchResult;

import java.util.List;

public class MajorElectiveMatchResult extends CreditMatchResult implements MatchResult {

    public MajorElectiveMatchResult(boolean matched, List<MatchEntry> matchEntries,
                                    int matchCredit, int totalCredit, double matchProgress) {
        super(matched, matchEntries, matchCredit, totalCredit, matchProgress);
    }

    @Override
    public String getRequirementName() {
        return "专业选修课";
    }

}
