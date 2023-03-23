package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ValidationTest {
    @Test
    void testStringSchema() {
        Validator v = new Validator();

        StringSchema schema = v.string();

        boolean actual = schema.isValid("");
        boolean actual1 = schema.isValid(null);
        Assertions.assertThat(actual).isTrue();
        Assertions.assertThat(actual1).isTrue();

        schema.required();

        boolean actual2 = schema.isValid("what does the fox say");
        boolean actual3 = schema.isValid("hexlet");
        boolean actual4 = schema.isValid(null);
        boolean actual5 = schema.isValid(5);
        boolean actual6 = schema.isValid("");

        Assertions.assertThat(actual2).isTrue();
        Assertions.assertThat(actual3).isTrue();
        Assertions.assertThat(actual4).isFalse();
        Assertions.assertThat(actual5).isFalse();
        Assertions.assertThat(actual6).isFalse();


        boolean actual7 = schema.contains("wh").isValid("what does the fox say");
        boolean actual8 = schema.contains("what").isValid("what does the fox say");
        boolean actual9 = schema.contains("whatthe").isValid("what does the fox say");
        boolean actual10 = schema.isValid("what does the fox say");
        Assertions.assertThat(actual7).isTrue();
        Assertions.assertThat(actual8).isTrue();
        Assertions.assertThat(actual9).isFalse();
        Assertions.assertThat(actual10).isFalse();
    }

    @Test
    void testNumberSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        boolean actual1 = schema.isValid(null);
        boolean actual2 = schema.positive().isValid(null);
        Assertions.assertThat(actual1).isTrue();
        Assertions.assertThat(actual2).isTrue();

        schema.required();

        boolean actual3 = schema.isValid(null);
        boolean actual4 = schema.isValid(10);
        boolean actual5 = schema.isValid("5");
        boolean actual6 = schema.isValid(-10);
        boolean actual7 = schema.isValid(0);

        Assertions.assertThat(actual3).isFalse();
        Assertions.assertThat(actual4).isTrue();
        Assertions.assertThat(actual5).isFalse();
        Assertions.assertThat(actual6).isFalse();
        Assertions.assertThat(actual7).isFalse();

        schema.range(5, 10);
        boolean actual8 = schema.isValid(5);
        boolean actual9 = schema.isValid(10);
        boolean actual10 = schema.isValid(4);
        boolean actual11 = schema.isValid(11);

        Assertions.assertThat(actual8).isTrue();
        Assertions.assertThat(actual9).isTrue();
        Assertions.assertThat(actual10).isFalse();
        Assertions.assertThat(actual11).isFalse();
    }

    @Test
    void testMapShema() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        boolean actual1 = schema.isValid(null);
        Assertions.assertThat(actual1).isTrue();

        schema.required();

        boolean actual2 = schema.isValid(null);
        boolean actual3 = schema.isValid(new HashMap());

        Assertions.assertThat(actual2).isFalse();
        Assertions.assertThat(actual3).isTrue();


        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        boolean actual4 = schema.isValid(data);
        Assertions.assertThat(actual4).isTrue();
        schema.sizeof(2);

        boolean actual5 = schema.isValid(data);
        data.put("key2", "value2");
        boolean actual6 = schema.isValid(data);

        Assertions.assertThat(actual5).isFalse();
        Assertions.assertThat(actual6).isTrue();
    }

    @Test
    void testParamsMap() {

        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        boolean actual = schema.isValid(human1);

        Assertions.assertThat(actual).isTrue();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        boolean actual1 = schema.isValid(human2);

        Assertions.assertThat(actual1).isTrue();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        boolean actual2 = schema.isValid(human3);

        Assertions.assertThat(actual2).isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        boolean actual3 = schema.isValid(human4);
        Assertions.assertThat(actual3).isFalse();

    }
}
