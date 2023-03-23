package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        addValid(p -> p instanceof Map);

    }
    public MapSchema required() {
        setRequired(true);
        return this;
    }
    public MapSchema sizeof(int size) {
        Predicate<Map> predicate = p -> p.size() == size;
        addValid(predicate);
        return this;

    }

    public MapSchema shape(Map<String, BaseSchema> map) {
        Predicate<Map> predicate = p -> checkValid(p, map);
        addValid(predicate);
        return this;
    }

    private Boolean checkValid(Map<String, String> map, Map<String, BaseSchema> valid) {
        for (Map.Entry<String, BaseSchema> val : valid.entrySet()) {
            String key = val.getKey();

            if (valid.containsKey(key) && !valid.get(key).isValid(map.get(key))) {
                return false;
            }
        }
        return true;
    }
}
