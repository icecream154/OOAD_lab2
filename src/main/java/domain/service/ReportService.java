package domain.service;

import domain.entity.Student;
import domain.value.MajorPreference;
import domain.value.report.Report;
import domain.value.requirement.base.Requirement;
import domain.value.matchResult.base.MatchResult;

import java.util.HashMap;
import java.util.Map;

public class ReportService {

    public static Report giveReport(Student student, String direction) {
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

        return new Report(student, direction, requirementMatchResultMap);
    }
}
