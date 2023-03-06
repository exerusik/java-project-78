package hexlet.code;

import java.util.function.Predicate;

public class NumberSchema extends SchemaBase{

    public NumberSchema() {
        addValid(p -> p instanceof Integer);
    }

    public NumberSchema required() {
        setRequired(true);
        return this;
    }


    public NumberSchema positive() {
        Predicate<Integer> numberPredicate = o -> (o > 0) ;
        addValid(numberPredicate);
        return this;
    }

    public NumberSchema range(Integer from, Integer to) {
        Predicate<Integer> predicateRange = p -> p >= from && p <= to;
        addValid(predicateRange);
        return this;
    }
}
