package application.loader;

import domain.factory.MajorFactory;
import domain.value.Major;
import domain.value.MajorPreference;
import domain.value.requirement.base.Requirement;
import domain.value.requirement.impl.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class MajorLoader {
    public static boolean load(String majorName, String path) {
        List<Requirement> generalRequirements = new ArrayList<>();
        List<DirectionRequirement> directionRequirements = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Scanner scanner = new Scanner(fileInputStream);
            if (scanner.hasNextLine()) {
                currentRequirementDef = scanner.nextLine();
            }

            List<String> basicCompulsoryCourseList = null;
            List<String> majorCompulsoryCourseList = null;
            while (currentRequirementDef != null) {
                String lastRequirementDef = currentRequirementDef;
                List<String> courseList = receiveCourseList(scanner);
                // 构造培养计划要求
                if (lastRequirementDef.startsWith("[Basic Compulsory]")) {
                    basicCompulsoryCourseList = courseList;
                    if (basicCompulsoryCourseList != null
                            && majorCompulsoryCourseList != null) {
                        basicCompulsoryCourseList.addAll(majorCompulsoryCourseList);
                        generalRequirements.add(buildBasicAndMajorCompulsoryRequirement(lastRequirementDef,
                                basicCompulsoryCourseList));
                    }
                    continue;
                }
                if (lastRequirementDef.startsWith("[Major Compulsory]")) {
                    majorCompulsoryCourseList = courseList;
                    if (basicCompulsoryCourseList != null
                            && majorCompulsoryCourseList != null) {
                        basicCompulsoryCourseList.addAll(majorCompulsoryCourseList);
                        generalRequirements.add(buildBasicAndMajorCompulsoryRequirement(lastRequirementDef,
                                basicCompulsoryCourseList));
                    }
                    continue;
                }
                if (lastRequirementDef.startsWith("[Major Elective]")) {
                    generalRequirements.add(buildMajorElectiveRequirement(lastRequirementDef, courseList));
                    continue;
                }
                if (lastRequirementDef.startsWith("[Module")) {
                    generalRequirements.add(buildModuleRequirement(lastRequirementDef, courseList));
                    continue;
                }
                if (lastRequirementDef.startsWith("[Direction")) {
                    directionRequirements.add(buildDirectionRequirement(lastRequirementDef, courseList));
                    continue;
                }
                if (lastRequirementDef.startsWith("[Other Elective]")) {
                    generalRequirements.add(buildOtherElectiveRequirement(lastRequirementDef));
                }
            }
            scanner.close();
            fileInputStream.close();
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            return false;
        }

        Map<String, MajorPreference> preferenceMap = new HashMap<>();
        if (directionRequirements.size() != 0) {
            for (DirectionRequirement directionRequirement : directionRequirements) {
                List<Requirement> cp = new ArrayList<>(generalRequirements);
                cp.add(directionRequirement);
                preferenceMap.put(directionRequirement.directionName,
                        new MajorPreference(directionRequirement.directionName,
                                cp, LoaderContext.globalCourseExchangeMap));
            }
        } else {
            preferenceMap.put("", new MajorPreference("", generalRequirements,
                    LoaderContext.globalCourseExchangeMap));
        }

        MajorFactory.registerMajor(new Major(majorName, preferenceMap));

        System.out.println("-- Major [" + majorName + "] Loading Success --");
        return true;
    }

    private static String currentRequirementDef;

    private static List<String> receiveCourseList(Scanner scanner) {
        List<String> res = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (currentLine.startsWith("[")) {
                currentRequirementDef = currentLine;
                return res;
            }
            res.add(currentLine);
        }
        currentRequirementDef = null;
        return res;
    }
    
    private static BasicCompulsoryRequirement buildBasicCompulsoryRequirement(
            String def, List<String> courseList) {
        return new BasicCompulsoryRequirement(courseList);
    }

    private static MajorCompulsoryRequirement buildMajorCompulsoryRequirement(
            String def, List<String> courseList) {
        return new MajorCompulsoryRequirement(courseList);
    }

    private static BasicAndMajorCompulsoryRequirement buildBasicAndMajorCompulsoryRequirement(
            String def, List<String> courseList) {
        return new BasicAndMajorCompulsoryRequirement(courseList);
    }

    private static MajorElectiveRequirement buildMajorElectiveRequirement(
            String def, List<String> courseList) {
        int totalCredit = Integer.parseInt(def.substring("[Major Elective] ".length()));
        return new MajorElectiveRequirement(courseList, totalCredit);
    }

    private static ModuleRequirement buildModuleRequirement(
            String def, List<String> courseList) {
        String[] defParsed = def.split(" ");
        String moduleName = defParsed[1].substring(0, defParsed[1].length() - 1);
        int totalCount = Integer.parseInt(defParsed[2]);
        return new ModuleRequirement(moduleName, courseList, totalCount);
    }

    private static DirectionRequirement buildDirectionRequirement(
            String def, List<String> courseList) {
        String[] defParsed = def.split(" ");
        String directionName = defParsed[1].substring(0, defParsed[1].length() - 1);
        int totalCredit = Integer.parseInt(defParsed[2]);
        return new DirectionRequirement(directionName, courseList, totalCredit);
    }

    private static OtherElectiveRequirement buildOtherElectiveRequirement(String def) {
        String[] defParsed = def.split(" ");
        return new OtherElectiveRequirement(Integer.parseInt(defParsed[defParsed.length - 1]));
    }
}
