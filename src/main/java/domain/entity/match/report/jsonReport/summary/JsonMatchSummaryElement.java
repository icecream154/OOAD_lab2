package domain.entity.match.report.jsonReport.summary;

import com.alibaba.fastjson.annotation.JSONField;
import domain.entity.match.matchResult.base.MatchEntry;
import domain.entity.match.matchResult.base.MatchResult;

public class JsonMatchSummaryElement {
    @JSONField(name = "课程类型", ordinal = 1)
    private String requirementName;

    @JSONField(name = "已修学分", ordinal = 2)
    private int learnedCredit;

    @JSONField(name = "已修课程数量", ordinal = 3)
    private int learnedCount;

    @JSONField(name = "要求学分/课程数量", ordinal = 4)
    private int requireNumber;

    @JSONField(name = "进度情况", ordinal = 5)
    private String progressInfo;

    public JsonMatchSummaryElement(String requirementName, int learnedCredit,
                                   int learnedCount, int requireNumber, String progressInfo) {
        this.requirementName = requirementName;
        this.learnedCredit = learnedCredit;
        this.learnedCount = learnedCount;
        this.requireNumber = requireNumber;
        this.progressInfo = progressInfo;
    }

    public JsonMatchSummaryElement(MatchResult matchResult) {
        this.requirementName = matchResult.getRequirementName();
        this.learnedCount = matchResult.getMatchEntries().size();
        int learnedCredit = 0;
        for (MatchEntry matchEntry : matchResult.getMatchEntries()) {
            learnedCredit += matchEntry.getRequireCourse().getCredit();
        }
        this.learnedCredit = learnedCredit;
        this.requireNumber = matchResult.getRequireNumber();
        this.progressInfo = (int)(matchResult.getMatchProgress() * 100) + "%";
    }

    public String getRequirementName() {
        return requirementName;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public int getLearnedCredit() {
        return learnedCredit;
    }

    public void setLearnedCredit(int learnedCredit) {
        this.learnedCredit = learnedCredit;
    }

    public int getLearnedCount() {
        return learnedCount;
    }

    public void setLearnedCount(int learnedCount) {
        this.learnedCount = learnedCount;
    }

    public int getRequireNumber() {
        return requireNumber;
    }

    public void setRequireNumber(int requireNumber) {
        this.requireNumber = requireNumber;
    }

    public String getProgressInfo() {
        return progressInfo;
    }

    public void setProgressInfo(String progressInfo) {
        this.progressInfo = progressInfo;
    }
}
