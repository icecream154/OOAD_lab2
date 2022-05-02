package domain.entity;

import java.util.HashMap;
import java.util.Map;

public class Major {
    private final String name;
    private final Map<String, MajorPreference> preferenceMap;

    public Major(String name) {
        this.name = name;
        this.preferenceMap = new HashMap<>();
    }

    public Major(String name, Map<String, MajorPreference> preferenceMap) {
        this.name = name;
        this.preferenceMap = preferenceMap;
    }

    public MajorPreference getMajorPreference(String direction) {
        return preferenceMap.get(direction);
    }

    public String getName() {
        return name;
    }

    public void registerPreference(MajorPreference preference) {
        this.preferenceMap.put(preference.getPreferenceName(), preference);
    }

    public Map<String, MajorPreference> getPreferenceMap() {
        return preferenceMap;
    }

    @Override
    public String toString() {
        return "Major{" +
                "name='" + name + '\'' +
                ", preference=" + preferenceMap.values() +
                '}';
    }
}
