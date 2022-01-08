package domain.value.report;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import domain.entity.Student;
import domain.value.report.jsonReport.JsonReport;
import domain.value.requirement.base.Requirement;
import domain.value.matchResult.base.MatchResult;

import java.util.Map;

public class Report {
    private final Student student;
    private final String direction;
    private final Map<Requirement, MatchResult> requirementMatchResultMap;

    public Report(Student student, String direction, Map<Requirement, MatchResult> requirementMatchResultMap) {
        this.student = student;
        this.direction = direction;
        this.requirementMatchResultMap = requirementMatchResultMap;
    }

    public String getJsonReport() {
        JsonReport jsonReport = new JsonReport(
                student.getId(),
                student.getName(),
                student.getMajor().getName(),
                direction,
                requirementMatchResultMap
        );
        return JSON.toJSONString(jsonReport, SerializerFeature.PrettyFormat);
    }
}