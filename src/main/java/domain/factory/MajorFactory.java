package domain.factory;

import domain.value.Major;

import java.util.HashMap;
import java.util.Map;

public class MajorFactory {
    private static final Map<String, Major> majorMap = new HashMap<>();

    public static Major registerMajor(Major major) {
        majorMap.put(major.getName(), major);
        return major;
    }

    public static Major getMajorByName(String name) {
        return majorMap.get(name);
    }
}
