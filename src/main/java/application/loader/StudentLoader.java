package application.loader;

import domain.entity.Course;
import domain.entity.Student;
import domain.factory.CourseFactory;
import domain.factory.MajorFactory;
import domain.factory.StudentFactory;
import domain.value.Major;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class StudentLoader {
    public static boolean load(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNextLine()) {
                String[] info = scanner.nextLine().split(", ");
                if (!(info.length == 3)) return false;
                // 注册学生
                Major major = MajorFactory.getMajorByName(info[2]);
                if (major == null) return false;

                Student student = StudentFactory.registerStudent(info[0], info[1], major);
                if (student == null) return false;
            }
            scanner.close();
            fileInputStream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }

        System.out.println("-- Student Loading Success --");
        return true;
    }
}
