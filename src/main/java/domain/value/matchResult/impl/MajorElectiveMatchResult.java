package domain.value.matchResult.impl;

import domain.value.matchResult.base.CreditMatchResult;
import domain.value.matchResult.base.MatchEntry;
import domain.value.matchResult.base.MatchResult;

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
