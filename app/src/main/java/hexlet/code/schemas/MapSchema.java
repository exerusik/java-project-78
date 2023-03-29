package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        addCheck(p -> p instanceof Map);

    }
    public MapSchema required() {
        setRequired(true);
        return this;
    }
    public MapSchema sizeof(int size) {
        Predicate<Map> predicate = p -> p.size() == size;
        addCheck(predicate);
        return this;

    }

    public MapSchema shape(Map<String, BaseSchema> map) {
        Predicate<Map> predicate = p -> checkValid(p, map);
        addCheck(predicate);
        return this;
    }

    private Boolean checkValid(Map<String, String> initialMap, Map<String, BaseSchema> validationMap) {
        for (Map.Entry<String, BaseSchema> val : validationMap.entrySet()) {
            String key = val.getKey();

            if (validationMap.containsKey(key) && !validationMap.get(key).isValid(initialMap.get(key))) {
                return false;
            }
        }
        return true;
    }
}
