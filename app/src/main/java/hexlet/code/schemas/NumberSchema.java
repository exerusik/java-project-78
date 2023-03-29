package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        addCheck(p -> p instanceof Integer);
    }

    public NumberSchema required() {
        setRequired(true);
        return this;
    }


    public NumberSchema positive() {
        Predicate<Integer> numberPredicate = p -> (p > 0);
        addCheck(numberPredicate);
        return this;
    }

    public NumberSchema range(Integer from, Integer to) {
        Predicate<Integer> predicateRange = p -> p >= from && p <= to;
        addCheck(predicateRange);
        return this;
    }
}
