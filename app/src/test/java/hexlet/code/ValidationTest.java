package hexlet.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidationTest {

    @Test
    void main() {
        Validator v = new Validator();

        StringSchema schema = v.string();

        boolean actual = schema.isValid(""); // true
 //Пока на вызван метод required(), null считается валидным
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


        schema.contains("wh").isValid("what does the fox say"); // true
        schema.contains("what").isValid("what does the fox say"); // true
        schema.contains("whatthe").isValid("what does the fox say"); // false

        schema.isValid("what does the fox say");// false
 //уже false, так как добавлена ещё одна проверка contains("whatthe")
    }
}
