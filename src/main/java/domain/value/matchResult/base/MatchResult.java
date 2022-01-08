package domain.value.matchResult.base;

import java.util.List;

public interface MatchResult {
    boolean isMatched();
    String getRequirementName();
    double getMatchProgress();
    int getMatchNumber();
    int getRequireNumber();
    List<MatchEntry> getMatchEntries();
}
