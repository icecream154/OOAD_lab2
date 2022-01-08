import application.Executor;
import application.loader.CourseLoader;
import application.loader.LearningRecordLoader;
import application.loader.MajorLoader;
import application.loader.StudentLoader;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean courseLoading = CourseLoader.load("inputs/Courses_Info.txt");
        if (!courseLoading) {
            System.out.println("course loading failed");
            return;
        }

        boolean majorLoading =
                MajorLoader.load("Computer Science",
                        "inputs/major/Computer Science.txt") &&
                MajorLoader.load("Network Engineering",
                        "inputs/major/Network Engineering.txt") &&
                MajorLoader.load("Software Engineering",
                        "inputs/major/Software Engineering.txt");
        if (!majorLoading) {
            System.out.println("major loading failed");
            return;
        }

        boolean studentLoading = StudentLoader.load("inputs/Students_Info.txt");
        if (!studentLoading) {
            System.out.println("student loading failed");
            return;
        }

        boolean learningRecordLoading = LearningRecordLoader.load("inputs/Learning.txt");
        if (!learningRecordLoading) {
            System.out.println("learning record loading failed");
            return;
        }
        System.out.println();

        Executor executor = new Executor();
        executor.showCommands();
        while(true) {
            System.out.print("-- Give Command : ");
            Scanner scanner = new Scanner(System.in);
            String rawCommand = scanner.nextLine();
            if (!executor.receiveCommand(rawCommand)) {
                break;
            }
        }
    }
}
