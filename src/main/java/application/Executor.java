package application;

import domain.entity.Student;
import domain.factory.LearningRecordFactory;
import domain.factory.MajorFactory;
import domain.factory.StudentFactory;
import domain.service.ReportService;
import domain.value.Major;
import domain.value.report.Report;

public class Executor {

    public boolean receiveCommand(String rawCommand) {
        if (rawCommand.startsWith("SHOW STUDENT")) {
            showStudent(rawCommand);
            return true;
        }
        if (rawCommand.startsWith("ADD RECORD")) {
            addRecord(rawCommand);
            return true;
        }
        if (rawCommand.startsWith("CHANGE MAJOR")) {
            changeMajor(rawCommand);
            return true;
        }
        if (rawCommand.startsWith("GIVE REPORT")) {
            giveReport(rawCommand);
            return true;
        }
        if (rawCommand.startsWith("QUIT")) {
            System.out.println("Program exit");
            return false;
        }

        System.out.println("Unknown command");
        showCommands();
        return true;
    }

    public void showCommands() {
        System.out.println("SUPPORT COMMANDS:");
        System.out.println("\t1. SHOW STUDENT <studentId>");
        System.out.println("\t2. ADD RECORD <studentId> <courseId>");
        System.out.println("\t3. CHANGE MAJOR <studentId> <major>");
        System.out.println("\t4. GIVE REPORT <studentId> [<direction>]");
        System.out.println("\t5. QUIT");
        System.out.println();
    }

    private boolean showStudent(String rawCommand) {
        String[] splits = rawCommand.split(" ");
        if (splits.length != 3) {
            System.out.println("Command format error: SHOW STUDENT <studentId>");
            return false;
        }
        Student student = StudentFactory.getStudentById(splits[2]);
        if (student == null) {
            System.out.println("Student not found");
            return false;
        }

        System.out.println(student);
        return true;
    }

    private boolean addRecord(String rawCommand) {
        String[] splits = rawCommand.split(" ");
        if (splits.length != 4) {
            System.out.println("Command format error: ADD RECORD <studentId> <courseId>");
            return false;
        }
        if (LearningRecordFactory.registerLearningRecord(splits[2], splits[3]) == null) {
            System.out.println("Student or course not found");
            return false;
        }
        System.out.println("Add success");
        return true;
    }

    private boolean changeMajor(String rawCommand) {
        String[] splits = rawCommand.split(" ");
        if (splits.length != 4) {
            System.out.println("Command format error: CHANGE MAJOR <studentId> <major>");
            return false;
        }
        String majorName = splits[3];
        Major major = MajorFactory.getMajorByName(majorName);
        if (major == null) {
            System.out.println("Major not found");
            return false;
        }
        Student student = StudentFactory.getStudentById(splits[2]);
        if (student == null) {
            System.out.println("Student not found");
            return false;
        }
        student.setMajor(major);
        if (!StudentFactory.save(student)) {
            System.out.println("Change major failed");
            return false;
        }
        System.out.println("Change major success");
        return true;
    }

    private boolean giveReport(String rawCommand) {
        String[] splits = rawCommand.split(" ");
        if (!(splits.length == 3 || splits.length == 4)) {
            System.out.println("Command format error: GIVE REPORT <studentId> [<direction>]");
            return false;
        }
        String direction = splits.length == 4 ? splits[3] : "";
        Student student = StudentFactory.getStudentById(splits[2]);
        if (student == null) {
            System.out.println("Student not found");
            return false;
        }
        Report report = ReportService.giveReport(student, direction);
        if (report == null) {
            System.out.println("No such direction");
            return false;
        }
        System.out.println(report.getJsonReport());
        return true;
    }
}
