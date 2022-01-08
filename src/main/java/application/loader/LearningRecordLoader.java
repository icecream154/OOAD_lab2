package application.loader;

import domain.entity.Course;
import domain.entity.LearningRecord;
import domain.factory.CourseFactory;
import domain.factory.LearningRecordFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LearningRecordLoader {
    public static boolean load(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNextLine()) {
                String[] info = scanner.nextLine().split(", ");
                if (info.length != 2) return false;
                // 添加学习记录
                LearningRecord learningRecord = LearningRecordFactory.registerLearningRecord(
                        info[0], info[1]
                );
                if (learningRecord == null) return false;
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            return false;
        }

        System.out.println("-- Learning Record Loading Success --");
        return true;
    }
}
