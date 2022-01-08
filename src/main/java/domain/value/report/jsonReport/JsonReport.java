package domain.value.report.jsonReport;


import com.alibaba.fastjson.annotation.JSONField;
import domain.value.matchResult.base.MatchResult;
import domain.value.report.jsonReport.detail.JsonMatchDetail;
import domain.value.report.jsonReport.summary.JsonMatchSummary;
import domain.value.requirement.base.Requirement;

import java.util.Map;

public class JsonReport {
    @JSONField(name = "学号", ordinal = 1)
    private String studentId;

    @JSONField(name = "学生名", ordinal = 2)
    private String studentName;

    @JSONField(name = "专业", ordinal = 3)
    private String majorName;

    @JSONField(name = "方向", ordinal = 4)
    private String direction;

    @JSONField(name = "进度汇总", ordinal = 5)
    private JsonMatchSummary progressSummary;

    @JSONField(name = "进度详情", ordinal = 6)
    private JsonMatchDetail progressDetail;

    public JsonReport(String studentId, String studentName, String majorName,
                      String direction, Map<Requirement, MatchResult> requirementMatchResultMap) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.majorName = majorName;
        this.direction = direction;

        this.progressSummary = new JsonMatchSummary(requirementMatchResultMap.values());
        this.progressDetail = new JsonMatchDetail(requirementMatchResultMap);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public JsonMatchSummary getProgressSummary() {
        return progressSummary;
    }

    public void setProgressSummary(JsonMatchSummary progressSummary) {
        this.progressSummary = progressSummary;
    }

    public JsonMatchDetail getProgressDetail() {
        return progressDetail;
    }

    public void setProgressDetail(JsonMatchDetail progressDetail) {
        this.progressDetail = progressDetail;
    }
}
