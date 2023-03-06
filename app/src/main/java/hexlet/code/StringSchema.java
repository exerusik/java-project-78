package hexlet.code;


import java.util.function.Predicate;

public class StringSchema extends SchemaBase{

    public StringSchema(){
        addValid(p -> p instanceof String && !validList.isEmpty());
    }

    public StringSchema required () {
        isRequired = true;
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<String> stringPredicate = p -> p.contains(str);
        addValid(stringPredicate);
        return this;

    }




    }
