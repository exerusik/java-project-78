package hexlet.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidationTest {

    @Test
    void testStringSchema() {
        Validator v = new Validator();

        StringSchema schema = v.string();

        boolean actual = schema.isValid(""); // true
        boolean actual1 = schema.isValid(null); // true
            Assertions.assertThat(actual).isTrue();
            Assertions.assertThat(actual1).isTrue();
        schema.required();

        boolean actual2 = schema.isValid("what does the fox say"); // true
        boolean actual3 = schema.isValid("hexlet"); // true
        boolean actual4 = schema.isValid(null); // false
        boolean actual5 = schema.isValid(5); // false
        boolean actual6 = schema.isValid(""); // false

        Assertions.assertThat(actual2).isTrue();
        Assertions.assertThat(actual3).isTrue();
        Assertions.assertThat(actual4).isFalse();
        Assertions.assertThat(actual5).isFalse();
        Assertions.assertThat(actual6).isFalse();


        boolean actual7 = schema.contains("wh").isValid("what does the fox say"); // true
        boolean actual8 = schema.contains("what").isValid("what does the fox say"); // true
        boolean actual9 = schema.contains("whatthe").isValid("what does the fox say"); // false
        boolean actual10 = schema.isValid("what does the fox say");// false

        Assertions.assertThat(actual7).isTrue();
        Assertions.assertThat(actual8).isTrue();
        Assertions.assertThat(actual9).isFalse();
        Assertions.assertThat(actual10).isFalse();
    }

    @Test
    void testNumberSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        boolean actual1 = schema.isValid(null); // true
        boolean actual2 = schema.positive().isValid(null); // true

        Assertions.assertThat(actual1).isTrue();
        Assertions.assertThat(actual2).isTrue();

        schema.required();

        boolean actual3 = schema.isValid(null); // false
        boolean actual4 = schema.isValid(10); // true
        boolean actual5 = schema.isValid("5"); // false
        boolean actual6 = schema.isValid(-10); // false
        boolean actual7 = schema.isValid(0); // false

        Assertions.assertThat(actual3).isFalse();
        Assertions.assertThat(actual4).isTrue();
        Assertions.assertThat(actual5).isFalse();
        Assertions.assertThat(actual6).isFalse();
        Assertions.assertThat(actual7).isFalse();

        schema.range(5, 10);
        boolean actual8 = schema.isValid(5); // true
        boolean actual9 = schema.isValid(10); // true
        boolean actual10 = schema.isValid(4); // false
        boolean actual11 = schema.isValid(11); // false

        Assertions.assertThat(actual8).isTrue();
        Assertions.assertThat(actual9).isTrue();
        Assertions.assertThat(actual10).isFalse();
        Assertions.assertThat(actual11).isFalse();
    }
}
