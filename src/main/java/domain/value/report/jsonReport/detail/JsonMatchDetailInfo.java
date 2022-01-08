package domain.value.report.jsonReport.detail;

import com.alibaba.fastjson.annotation.JSONField;
import domain.value.matchResult.base.CompulsoryMatchResult;
import domain.value.matchResult.base.CountMatchResult;
import domain.value.matchResult.base.CreditMatchResult;
import domain.value.matchResult.base.MatchResult;
import domain.value.matchResult.impl.OtherElectiveMatchResult;

public class JsonMatchDetailInfo implements JsonMatchDetailElement {
    @JSONField(name = "总结", ordinal = 1)
    String summary;

    @JSONField(name = "备注", ordinal = 2)
    String extraInfo;

    public JsonMatchDetailInfo(MatchResult matchResult) {
        if (matchResult instanceof CreditMatchResult || matchResult instanceof CompulsoryMatchResult) {
            int less = matchResult.getRequireNumber() - matchResult.getMatchNumber();
            less = Math.max(less, 0);
            summary = "要求" + matchResult.getRequireNumber()
                    + "学分，缺少" + less + "学分。";
            extraInfo = (int)(matchResult.getMatchProgress() * 100) + "%";
            return;
        }

        if (matchResult instanceof CountMatchResult) {
            int less = matchResult.getRequireNumber() - matchResult.getMatchNumber();
            less = Math.max(less, 0);
            summary = "要求" + matchResult.getRequireNumber()
                    + "门课，缺少" + less + "门课。";
            extraInfo = (int)(matchResult.getMatchProgress() * 100) + "%";
            return;
        }

        if (matchResult instanceof OtherElectiveMatchResult) {
            int less = matchResult.getRequireNumber() - matchResult.getMatchNumber();
            less = Math.max(less, 0);
            int more = matchResult.getMatchNumber() - matchResult.getRequireNumber();
            more = Math.max(more, 0);
            summary = "要求" + matchResult.getRequireNumber()
                    + "学分，缺少" + less + "学分，超出" + more + "学分。";
            extraInfo = (int)(matchResult.getMatchProgress() * 100) + "%";
        }
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
