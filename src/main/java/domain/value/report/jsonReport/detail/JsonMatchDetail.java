package domain.value.report.jsonReport.detail;

import com.alibaba.fastjson.JSONObject;
import domain.value.matchResult.base.MatchResult;
import domain.value.matchResult.impl.*;
import domain.value.requirement.base.Requirement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonMatchDetail extends JSONObject {

    public JsonMatchDetail(Map<Requirement, MatchResult> requirementMatchResultMap) {
        super(true);
        MatchResult compulsoryResult = null;
        MatchResult majorElectiveResult = null;
        List<MatchResult> moduleResults = new ArrayList<>();
        MatchResult otherElectiveResult = null;
        MatchResult directionResult = null;

        for (MatchResult matchResult : requirementMatchResultMap.values()) {
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
            this.put(compulsoryResult.getRequirementName(), new JsonMatchDetailList(compulsoryResult));
        }
        if (majorElectiveResult != null) {
            this.put(majorElectiveResult.getRequirementName(), new JsonMatchDetailList(majorElectiveResult));
        }
        for (MatchResult matchResult : moduleResults) {
            this.put(matchResult.getRequirementName(), new JsonMatchDetailList(matchResult));
        }
        if (directionResult != null) {
            this.put(directionResult.getRequirementName(), new JsonMatchDetailList(directionResult));
        }
        if (otherElectiveResult != null) {
            this.put(otherElectiveResult.getRequirementName(), new JsonMatchDetailList(otherElectiveResult));
        }

    }
}
