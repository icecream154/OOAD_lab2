package application.loader;

import domain.entity.LearningRecord;
import domain.factory.LearningRecordFactory;

import java.io.FileInputStream;
import java.io.IOException;
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
            scanner.close();
            fileInputStream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }

        System.out.println("-- Learning Record Loading Success --");
        return true;
    }
}
