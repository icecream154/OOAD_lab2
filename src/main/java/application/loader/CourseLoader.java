package application.loader;

import domain.entity.Course;
import domain.factory.CourseFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CourseLoader {
    public static boolean load(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNextLine()) {
                String[] info = scanner.nextLine().split(", ");
                if (!(info.length == 3 || info.length == 4)) return false;
                // 注册课程
                Course course = CourseFactory.registerCourse(info[0], info[1], Integer.parseInt(info[2]));
                if (course == null) return false;
                // 配置全局替换
                if (info.length == 4 && !info[3].equals("none") && !info[3].equals("exchange")) {
                    LoaderContext.globalCourseExchangeMap.put(info[3], info[0]);
                    LoaderContext.globalCourseExchangeMap.put(info[0], info[3]);
                    // System.out.println("exchange config: [" + info[3] + "] exchange [" + info[0] + "]");
                }
            }
            scanner.close();
            fileInputStream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }

        System.out.println("-- Course Loading Success --");
        return true;
    }
}
