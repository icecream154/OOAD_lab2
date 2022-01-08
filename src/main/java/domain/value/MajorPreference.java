package domain.value;

import domain.value.requirement.base.Requirement;

import java.util.List;
import java.util.Map;

public class MajorPreference {
    // 方向名称
    private String preferenceName;
    // 培养计划需要满足的规则列表
    private List<Requirement> requirements;
    // 课程替换表，条目<"SOFT001","COMP002">表示"SOFT001"可以替换"COMP002"
    private Map<String, String> courseExchangeMap;

    public MajorPreference(String preferenceName, List<Requirement> requirements,
                           Map<String, String> courseExchangeMap) {
        this.preferenceName = preferenceName;
        this.requirements = requirements;
        this.courseExchangeMap = courseExchangeMap;
    }

    public String getPreferenceName() {
        return preferenceName;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public Map<String, String> getCourseExchangeMap() {
        return courseExchangeMap;
    }

    @Override
    public String toString() {
        return "MajorPreference{" +
                "preferenceName='" + preferenceName + '\'' +
                ", requirements=" + requirements +
                '}';
    }
}
