package hexlet.code.schemas;


import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        addValid(p -> p instanceof String);
    }

    public StringSchema required() {
        setRequired(true);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<String> stringPredicate = p -> p.contains(str);
        addValid(stringPredicate);
        return this;

    }
    public StringSchema minLength(int length) {
        Predicate<String> predicate = p -> p.length() >= length;
        addValid(predicate);
        return this;
    }
}
