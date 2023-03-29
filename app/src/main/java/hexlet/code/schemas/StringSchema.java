package hexlet.code.schemas;


import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        addCheck(p -> p instanceof String);
    }

    public StringSchema required() {
        setRequired(true);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<String> stringPredicate = p -> p.contains(str);
        addCheck(stringPredicate);
        return this;

    }
    public StringSchema minLength(int length) {
        Predicate<String> predicate = p -> p.length() >= length;
        addCheck(predicate);
        return this;
    }
}
