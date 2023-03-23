package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    private List<Predicate> validList = new ArrayList<>();

    private boolean isRequired = false;

    public final void addValid(Predicate predicate) {
        validList.add(predicate);

    }

    public final boolean isValid(Object object) {
        if (!isRequired && (object == null || object.toString().isEmpty())) {
            return  true;
        } else if (isRequired && (object == null || object.toString().isEmpty())) {
            return false;
        }
        for (Predicate predicate : validList) {
            if (!predicate.test(object)) {
                return false;
            }
        }
        return true;
    }

    public final void setRequired(boolean required) {
        isRequired = required;
    }
}
