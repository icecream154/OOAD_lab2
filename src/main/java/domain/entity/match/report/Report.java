package domain.entity.match.report;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import domain.entity.Student;
import domain.entity.match.report.jsonReport.JsonReport;
import domain.entity.requirement.base.Requirement;
import domain.entity.match.matchResult.base.MatchResult;

import java.util.Map;

public class Report {
    private final Student student;
    private final String direction;
    private final Map<Requirement, MatchResult> requirementMatchResultMap;
    private final JsonReport jsonReport;

    public Report(Student student, String direction, Map<Requirement, MatchResult> requirementMatchResultMap) {
        this.student = student;
        this.direction = direction;
        this.requirementMatchResultMap = requirementMatchResultMap;
        this.jsonReport = new JsonReport(
                student.getId(),
                student.getName(),
                student.getMajor().getName(),
                direction,
                requirementMatchResultMap
        );
    }

    public Student getStudent() {
        return student;
    }

    public String getDirection() {
        return direction;
    }

    public Map<Requirement, MatchResult> getRequirementMatchResultMap() {
        return requirementMatchResultMap;
    }

    public JsonReport getJsonReport() {
        return jsonReport;
    }

    public String getPrettyJsonReport() {
        return JSON.toJSONString(this.jsonReport, SerializerFeature.PrettyFormat);
    }
}