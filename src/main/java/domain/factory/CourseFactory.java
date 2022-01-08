package domain.factory;

import domain.entity.Course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseFactory {

    private static final Map<String, Course> courseMap = new HashMap<>();

    public static Course registerCourse(String courseId, String courseName, int courseCredit) {
        Course oldCourse = getCourseById(courseId);
        if (oldCourse != null) return null;
        Course course = new Course(courseId, courseName, courseCredit);
        courseMap.put(courseId, course);
        return course;
    }

    public static Course getCourseById(String courseId) {
        return courseMap.get(courseId);
    }

    public static Map<String, Course> getCourseMapFromIds(List<String> courseIds) {
        Map<String, Course> res = new HashMap();
        for (String courseId : courseIds) {
            Course course = courseMap.get(courseId);
            if (course != null) res.put(courseId, course);
        }
        return res;
    }
}
