package domain.entity.match.matchResult.impl;

import domain.entity.match.matchResult.base.CreditMatchResult;
import domain.entity.match.matchResult.base.MatchEntry;
import domain.entity.match.matchResult.base.MatchResult;

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
