package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileUtilsTest {

    private static final String NOT_A_FILE_PATH = "src/test/resources/file.file";
    private static final String VALID_FILE_PATH = "src/test/resources/json/json1.json";

    @Test
    void testExceptionWhenInputNotAFilePath() {
        assertThrows(NoSuchFileException.class, () -> FileUtils.readFile(NOT_A_FILE_PATH));
    }

    @Test
    void testWhenInputFileNotValidDataFormat() {
        assertThat(FileUtils.getDataFormat(NOT_A_FILE_PATH)).isNotEmpty()
                .doesNotContain("json", "yaml")
                .isEqualTo("file");
    }

    @Test
    void testReturnContent() throws Exception {
        assertThat(FileUtils.readFile(VALID_FILE_PATH)).isNotEmpty()
                .isEqualTo("{\n"
                        + "  \"setting1\": \"Some value\",\n"
                        + "  \"setting2\": 200,\n"
                        + "  \"setting3\": true,\n"
                        + "  \"key1\": \"value1\",\n"
                        + "  \"numbers1\": [1, 2, 3, 4],\n"
                        + "  \"numbers2\": [2, 3, 4, 5],\n"
                        + "  \"id\": 45,\n"
                        + "  \"default\": null,\n"
                        + "  \"checked\": false,\n"
                        + "  \"numbers3\": [3, 4, 5],\n"
                        + "  \"chars1\": [\"a\", \"b\", \"c\"],\n"
                        + "  \"chars2\": [\"d\", \"e\", \"f\"]\n"
                        + "}");
    }
}
