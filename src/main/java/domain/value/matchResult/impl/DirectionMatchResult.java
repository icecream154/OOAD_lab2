package domain.value.matchResult.impl;

import domain.value.matchResult.base.CreditMatchResult;
import domain.value.matchResult.base.MatchEntry;
import domain.value.matchResult.base.MatchResult;

import java.util.List;

public class DirectionMatchResult extends CreditMatchResult implements MatchResult {
    private String directionName;

    public DirectionMatchResult(String directionName, boolean matched, List<MatchEntry> matchEntries,
                                int matchCredit, int totalCredit, double matchProgress) {
        super(matched, matchEntries, matchCredit, totalCredit, matchProgress);
        this.directionName = directionName;
    }

    @Override
    public String getRequirementName() {
        return "方向课 " + directionName;
    }
}
