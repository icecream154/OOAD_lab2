package domain.service;

import domain.entity.Student;
import domain.entity.MajorPreference;
import domain.entity.match.report.Report;
import domain.entity.requirement.base.Requirement;
import domain.entity.match.matchResult.base.MatchResult;

import java.util.HashMap;
import java.util.Map;

public class ReportService {

    private static Map<String, Report> cachedReports = new HashMap<>();

    public static Report giveReport(Student student, String direction, boolean allowCache) {
        String reportKey = student.getId() + "-" + direction;
        // 读取预先计算的缓存
        if (allowCache) {
            Report cached = cachedReports.get(reportKey);
            if (cached != null) {
                return cached;
            }
        }

        MajorPreference majorPreference = student.getMajor().getMajorPreference(direction);
        if (majorPreference == null) return null;

        Map<Requirement, MatchResult> requirementMatchResultMap = new HashMap<>();
        for (Requirement requirement : majorPreference.getRequirements()) {
            requirementMatchResultMap.put(requirement, requirement.matchRequirement(
                    student.getLearningRecordList(),
                    majorPreference.getRequirements(),
                    majorPreference.getCourseExchangeMap()
            ));
        }

        Report report = new Report(student, direction, requirementMatchResultMap);
        cachedReports.put(reportKey, report);
        return report;
    }
}
