package domain.entity.requirement.base;

import domain.entity.Course;
import domain.entity.LearningRecord;
import domain.factory.CourseFactory;
import domain.entity.match.matchResult.base.MatchEntry;

import java.util.*;

/**
 * 培养要求抽象基类
 * 实现公共方法：通过修读记录以及学分转换规则获取要求中匹配的课程列表
 */
public abstract class AbstractRequirement {

    protected List<MatchEntry> getMatchedEntries(final Map<String, Course> courseMap,
                                                 final List<LearningRecord> learningRecordList,
                                                 final Map<String, String> courseExchangeMap) {
        // 修读的课程
        Map<String, Course> learningMap = CourseFactory.getCourseMapFromIds(
                LearningRecord.getCourseIdList(learningRecordList));

        // 初始化匹配结果
        List<MatchEntry> matchEntries = new ArrayList<>();

        // 不转换学分下首先匹配
        Collection<Course> firstMatchedCourses = new HashMap<>(courseMap).values();
        firstMatchedCourses.retainAll(learningMap.values());
        // 加入结果集
        for (Course firstMatchedCourse : firstMatchedCourses) {
            matchEntries.add(new MatchEntry(firstMatchedCourse, false, null));
        }

        // 从未成功匹配的修读记录中使学分互换生效，进行二次匹配
        Collection<Course> leftLearningCourses = learningMap.values();
        leftLearningCourses.removeAll(firstMatchedCourses); // 移除已匹配成功的课程

        List<String> exchangeCourses = new ArrayList<>();
        Map<String, MatchEntry> exchangeMatchEntry = new HashMap<>();
        for (Course course : leftLearningCourses) {
            String exchangeCourseId = courseExchangeMap.get(course.getId());
            if (exchangeCourseId != null) {
                exchangeCourses.add(exchangeCourseId);
                exchangeMatchEntry.put(exchangeCourseId,
                        new MatchEntry(courseMap.get(exchangeCourseId), true, course));
            }
        }
        Map<String, Course> exchangeCourseMap = CourseFactory.getCourseMapFromIds(exchangeCourses);

        // 二次匹配
        Collection<Course> leftMatchedCourses = courseMap.values();
        leftMatchedCourses.removeAll(firstMatchedCourses);
        leftMatchedCourses.retainAll(exchangeCourseMap.values());
        // 加入结果集
        for (Course leftMatchedCourse : leftMatchedCourses) {
            matchEntries.add(exchangeMatchEntry.get(leftMatchedCourse.getId()));
        }

        return matchEntries;
    }

}
