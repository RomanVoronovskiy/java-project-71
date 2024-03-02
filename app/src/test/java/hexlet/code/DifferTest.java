package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DifferTest {

    private static final String STYLISH_FORMAT = "stylish";
    private static final String PLAIN_FORMAT = "plain";
    private static final String JSON_FORMAT = "json";
    private static String stylishExpectedDataString;
    private static String plainExpectedDataString;
    private static String jsonExpectedDataString;

    @BeforeAll
    static void init() throws Exception {
        stylishExpectedDataString = FileUtils.readFile(getPathByFileNameAndExpected("stylish.txt", true));
        plainExpectedDataString = FileUtils.readFile(getPathByFileNameAndExpected("plain.txt", true));
        jsonExpectedDataString = FileUtils.readFile(getPathByFileNameAndExpected("json.txt", true));
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
                arguments(getPathByFileNameAndExpected("json1.json"), getPathByFileNameAndExpected("json2.json"),
                        stylishExpectedDataString, STYLISH_FORMAT),
                arguments(getPathByFileNameAndExpected("json1.json"), getPathByFileNameAndExpected("json2.json"),
                        plainExpectedDataString, PLAIN_FORMAT),
                arguments(getPathByFileNameAndExpected("json1.json"), getPathByFileNameAndExpected("json2.json"),
                        jsonExpectedDataString, JSON_FORMAT),
                arguments(getPathByFileNameAndExpected("yaml1.yml"), getPathByFileNameAndExpected("yaml2.yml"),
                        stylishExpectedDataString, STYLISH_FORMAT),
                arguments(getPathByFileNameAndExpected("yaml1.yml"), getPathByFileNameAndExpected("yaml2.yml"),
                        plainExpectedDataString, PLAIN_FORMAT),
                arguments(getPathByFileNameAndExpected("yaml1.yml"), getPathByFileNameAndExpected("yaml2.yml"),
                        jsonExpectedDataString, JSON_FORMAT)
        );
    }

    public static Stream<Arguments> getExpectedStringsForNotFormats() {
        return Stream.of(
                arguments(getPathByFileNameAndExpected("json1.json"), getPathByFileNameAndExpected("json2.json"), stylishExpectedDataString),
                arguments(getPathByFileNameAndExpected("yaml1.yml"), getPathByFileNameAndExpected("yaml2.yml"), stylishExpectedDataString)
        );
    }

    private static String getPathByFileNameAndExpected(String fileName, boolean expected) {
        String baseExpectedPath = "src/test/resources/expected/";
        String baseJsonPath = "src/test/resources/json/";
        String baseYamlPath = "src/test/resources/yaml/";
        if (expected) {
            return baseExpectedPath + fileName;
        } else if (fileName.contains(".json")) {
            return baseJsonPath + fileName;
        } else {
            return baseYamlPath + fileName;
        }
    }

    private static String getPathByFileNameAndExpected(String fileName) {
        return getPathByFileNameAndExpected(fileName, false);
    }


}
