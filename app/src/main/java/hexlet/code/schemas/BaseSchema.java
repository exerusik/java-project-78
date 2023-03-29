package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    private List<Predicate> checks = new ArrayList<>();

    private boolean isRequired = false;

    public final void addCheck(Predicate predicate) {
        checks.add(predicate);

    }

    public final boolean isValid(Object object) {
        if (!isRequired && (object == null || object.equals(""))) {
            return  true;
        } else if (isRequired && (object == null || object.equals(""))) {
            return false;
        }
        for (Predicate predicate : checks) {
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
