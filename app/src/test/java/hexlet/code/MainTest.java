package hexlet.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void main() {
        String actual = "hello";
        Assertions.assertThat(actual).isEqualTo("hello");
    }
}
