package application.loader;

import java.util.HashMap;
import java.util.Map;

public class LoaderContext {
    // 课程替换表，条目<"SOFT001","COMP002">表示"SOFT001"可以替换"COMP002"
    public static Map<String, String> globalCourseExchangeMap = new HashMap<>();
}
