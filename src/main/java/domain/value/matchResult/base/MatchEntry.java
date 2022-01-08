package domain.value.matchResult.base;

import domain.entity.Course;

public class MatchEntry {
    private final Course requireCourse;
    private final boolean isExchange;
    private final Course exchangeCourse;

    public MatchEntry(Course requireCourse, boolean isExchange, Course exchangeCourse) {
        this.requireCourse = requireCourse;
        this.isExchange = isExchange;
        this.exchangeCourse = exchangeCourse;
    }

    public Course getRequireCourse() {
        return requireCourse;
    }

    public boolean isExchange() {
        return isExchange;
    }

    public Course getExchangeCourse() {
        return exchangeCourse;
    }

    @Override
    public String toString() {
        return "MatchEntry{" +
                "requireCourse=" + requireCourse.getId() + "-" + requireCourse.getName() +
                ", isExchange=" + isExchange +
                ", exchangeCourse=" + (exchangeCourse != null ? (exchangeCourse.getId() + "-" + exchangeCourse.getName()) : "") +
                '}';
    }
}
