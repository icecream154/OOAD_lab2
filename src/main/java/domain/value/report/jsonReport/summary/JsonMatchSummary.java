package domain.value.report.jsonReport.summary;

import domain.value.matchResult.base.MatchEntry;
import domain.value.matchResult.base.MatchResult;
import domain.value.matchResult.impl.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JsonMatchSummary extends ArrayList<JsonMatchSummaryElement> {
    public JsonMatchSummary(Collection<MatchResult> matchResults) {
        MatchResult compulsoryResult = null;
        MatchResult majorElectiveResult = null;
        List<MatchResult> moduleResults = new ArrayList<>();
        MatchResult otherElectiveResult = null;
        MatchResult directionResult = null;

        for (MatchResult matchResult : matchResults) {
            if (matchResult instanceof BasicAndMajorCompulsoryMatchResult) {
                compulsoryResult = matchResult;
                continue;
            }
            if (matchResult instanceof MajorElectiveMatchResult) {
                majorElectiveResult = matchResult;
                continue;
            }
            if (matchResult instanceof ModuleMatchResult) {
                moduleResults.add(matchResult);
                continue;
            }
            if (matchResult instanceof OtherElectiveMatchResult) {
                otherElectiveResult = matchResult;
                continue;
            }
            if (matchResult instanceof DirectionMatchResult) {
                directionResult = matchResult;
            }
        }

        if (compulsoryResult != null) {
            this.add(new JsonMatchSummaryElement(compulsoryResult));
        }
        if (majorElectiveResult != null) {
            this.add(new JsonMatchSummaryElement(majorElectiveResult));
        }
        for (MatchResult matchResult : moduleResults) {
            this.add(new JsonMatchSummaryElement(matchResult));
        }
        if (otherElectiveResult != null) {
            int learnedCredit = 0;
            for (MatchEntry matchEntry : otherElectiveResult.getMatchEntries()) {
                learnedCredit += matchEntry.getRequireCourse().getCredit();
            }
            this.add(new JsonMatchSummaryElement(
                    otherElectiveResult.getRequirementName(),
                    learnedCredit,
                    otherElectiveResult.getMatchEntries().size(),
                    otherElectiveResult.getRequireNumber(),
                    (int)(otherElectiveResult.getMatchProgress() * 100) + "%"
            ));
        }
        if (directionResult != null) {
            this.add(new JsonMatchSummaryElement(directionResult));
        }
    }
}
