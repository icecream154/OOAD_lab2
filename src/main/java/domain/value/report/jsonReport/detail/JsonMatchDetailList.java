package domain.value.report.jsonReport.detail;

import domain.value.matchResult.base.MatchEntry;
import domain.value.matchResult.base.MatchResult;
import domain.value.matchResult.impl.OtherElectiveMatchResult;

import java.util.ArrayList;
import java.util.Map;

public class JsonMatchDetailList extends ArrayList<JsonMatchDetailElement> {
    public JsonMatchDetailList(MatchResult matchResult) {

        for (MatchEntry matchEntry : matchResult.getMatchEntries()) {
            this.add(new JsonMatchDetailEntry(matchEntry));
        }

        if (matchResult instanceof OtherElectiveMatchResult) {
            Map<MatchResult, Integer> requirementExtraCredit
                    = ((OtherElectiveMatchResult) matchResult).getRequirementExtraCredit();

            for (MatchResult extraMatchResult : requirementExtraCredit.keySet()) {
                this.add(new JsonMatchDetailEntry(
                        extraMatchResult.getRequirementName() + "超出学分",
                        requirementExtraCredit.get(extraMatchResult),
                        "null"
                ));
            }
        }

        this.add(new JsonMatchDetailInfo(matchResult));
    }
}
