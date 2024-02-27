package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DifferTest {

    private static final String STR_JSON_PATH_1 = "src/test/resources/json/json1.json";
    private static final String STR_JSON_PATH_2 = "src/test/resources/json/json2.json";
    private static final String STR_YAML_PATH_1 = "src/test/resources/yaml/yaml1.yml";
    private static final String STR_YAML_PATH_2 = "src/test/resources/yaml/yaml2.yml";
    private static final String EXPECTED_STYLISH_STR_PATH = "src/test/resources/expected/stylish.txt";
    private static final String EXPECTED_PLAIN_STR_PATH = "src/test/resources/expected/plain.txt";
    private static final String EXPECTED_JSON_STR_PATH = "src/test/resources/expected/json.txt";
    private static final String STYLISH_FORMAT = "stylish";
    private static final String PLAIN_FORMAT = "plain";
    private static final String JSON_FORMAT = "json";

    private static String stylishExpectedDataString;
    private static String plainExpectedDataString;
    private static String jsonExpectedDataString;

    @BeforeAll
    static void init() throws Exception {
        stylishExpectedDataString = FileUtils.readFile(EXPECTED_STYLISH_STR_PATH);
        plainExpectedDataString = FileUtils.readFile(EXPECTED_PLAIN_STR_PATH);
        jsonExpectedDataString = FileUtils.readFile(EXPECTED_JSON_STR_PATH);
    }


    @ParameterizedTest
    @MethodSource("getExpectedStringsForFormats")
    void testDiffsWhenInputFilesWithFormat(String pathOne, String pathTwo,
                                           String expected, String format) throws Exception {
        String actual = Differ.generate(pathOne, pathTwo, format);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("getExpectedStringsForNotFormats")
    void testDiffsWhenInputFilesNotFormat(String pathOne, String pathTwo, String expected) throws Exception {
        String actual = Differ.generate(pathOne, pathTwo);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getExpectedStringsForFormats() {
        return Stream.of(
                arguments(STR_JSON_PATH_1, STR_JSON_PATH_2, stylishExpectedDataString, STYLISH_FORMAT),
                arguments(STR_JSON_PATH_1, STR_JSON_PATH_2, plainExpectedDataString, PLAIN_FORMAT),
                arguments(STR_JSON_PATH_1, STR_JSON_PATH_2, jsonExpectedDataString, JSON_FORMAT),
                arguments(STR_YAML_PATH_1, STR_YAML_PATH_2, stylishExpectedDataString, STYLISH_FORMAT),
                arguments(STR_YAML_PATH_1, STR_YAML_PATH_2, plainExpectedDataString, PLAIN_FORMAT),
                arguments(STR_YAML_PATH_1, STR_YAML_PATH_2, jsonExpectedDataString, JSON_FORMAT)
        );
    }

    public static Stream<Arguments> getExpectedStringsForNotFormats() {
        return Stream.of(
                arguments(STR_JSON_PATH_1, STR_JSON_PATH_2, stylishExpectedDataString),
                arguments(STR_YAML_PATH_1, STR_YAML_PATH_2, stylishExpectedDataString)
        );
    }


}
