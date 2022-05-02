package domain.entity.match.report.jsonReport.detail;

import com.alibaba.fastjson.annotation.JSONField;
import domain.entity.Course;
import domain.entity.match.matchResult.base.MatchEntry;

public class JsonMatchDetailEntry implements JsonMatchDetailElement {
    @JSONField(name = "课程", ordinal = 1)
    String courseName;

    @JSONField(name = "学分", ordinal = 2)
    int credit;

    @JSONField(name = "备注", ordinal = 3)
    String extraInfo;

    public JsonMatchDetailEntry(MatchEntry matchEntry) {
        Course requireCourse = matchEntry.getRequireCourse();
        Course exchangeCourse = matchEntry.getExchangeCourse();
        this.courseName = requireCourse.getId() + "-" + requireCourse.getName();
        this.credit = matchEntry.getRequireCourse().getCredit();
        if (matchEntry.isExchange() && exchangeCourse != null) {
            this.extraInfo = exchangeCourse.getId() + "-" + exchangeCourse.getName();
        } else {
            this.extraInfo = "null";
        }
    }

    public JsonMatchDetailEntry(String courseName, int credit, String extraInfo) {
        this.courseName = courseName;
        this.credit = credit;
        this.extraInfo = extraInfo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
