package hexlet.code;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SchemaBase {

    List<Predicate> validList = new ArrayList<>();

    public boolean isRequired = false;

    public void addValid(Predicate predicate) {
        validList.add(predicate);

    }

    public boolean isValid(Object object) {
        if(!isRequired && (object == null || object.equals(""))) {
            return  true;
        } else if (isRequired && (object == null || object.equals(""))){
            return false;
        }
        for (Predicate predicate : validList) {
            if (!predicate.test(object)){
                return false;
            }
        }
        return true;
    }




}