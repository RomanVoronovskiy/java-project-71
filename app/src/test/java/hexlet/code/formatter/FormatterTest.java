package hexlet.code.formatter;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

class FormatterTest {

    private static final String INVALID_EXTENSION = "txt";

    @Test
    void testExceptionIfExtensionIsNotSupported() {
        assertThatThrownBy(() -> Formatter.getFormat(new HashMap<>(), INVALID_EXTENSION))
                .isInstanceOf(Exception.class)
                .hasMessage("Formatting error");
    }
}
