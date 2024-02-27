package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DifferencesTest {
    private static final String STR_JSON_PATH_1 = "src/test/resources/json/json1.json";
    private static final String STR_JSON_PATH_2 = "src/test/resources/json/json2.json";
    private static final String STR_YAML_PATH_1 = "src/test/resources/yaml/yaml1.yml";
    private static final String STR_YAML_PATH_2 = "src/test/resources/yaml/yaml2.yml";
    private static final String JSON_FORMAT = "json";
    private static final String YAML_FORMAT = "yaml";
    public static final int TWENTY_TWO = 22;
    public static final int THREE = 3;
    public static final int THIRTY_THREE = 33;
    public static final int FOUR = 4;
    public static final int FORTY_FOUR = 44;
    public static final int FORTY_FIVE = 45;
    public static final int FIFTY_FIVE = 55;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int TWO_HUNDRED = 200;
    public static final int THREE_HUNDRED = 300;

    private static Map<String, Object> jsonExpectedDataOne;
    private static Map<String, Object> jsonExpectedDataTwo;
    private static Map<String, Object> yamlExpectedDataOne;
    private static Map<String, Object> yamlExpectedDataTwo;
    private static Map<String, Item> expectedMap;

    @BeforeAll
    static void init() throws Exception {
        String jsonExpectedContentOne = FileUtils.readFile(STR_JSON_PATH_1);
        String jsonExpectedContentTwo = FileUtils.readFile(STR_JSON_PATH_2);
        String yamlExpectedContentOne = FileUtils.readFile(STR_YAML_PATH_1);
        String yamlExpectedContentTwo = FileUtils.readFile(STR_YAML_PATH_2);
        jsonExpectedDataOne = Parser.getData(jsonExpectedContentOne, JSON_FORMAT);
        jsonExpectedDataTwo = Parser.getData(jsonExpectedContentTwo, JSON_FORMAT);
        yamlExpectedDataOne = Parser.getData(yamlExpectedContentOne, YAML_FORMAT);
        yamlExpectedDataTwo = Parser.getData(yamlExpectedContentTwo, YAML_FORMAT);
        expectedMap = new LinkedHashMap<>();

        expectedMap.put("chars1", new Item(Arrays.asList("a", "b", "c"), Arrays.asList("a", "b", "c"), "unchanged"));
        expectedMap.put("chars2", new Item(Arrays.asList("d", "e", "f"), false, "changed"));
        expectedMap.put("checked", new Item(false, true, "changed"));
        expectedMap.put("default", new Item(null, Arrays.asList("value1", "value2"), "changed"));
        expectedMap.put("id", new Item(FORTY_FIVE, null, "changed"));
        expectedMap.put("key1", new Item("value1", null, "deleted"));
        expectedMap.put("key2", new Item("value2", null, "added"));
        expectedMap.put("numbers1", new Item(
                Arrays.asList(1, 2, THREE, FOUR),
                Arrays.asList(1, 2, THREE, FOUR),
                "unchanged"));
        expectedMap.put("numbers2", new Item(
                Arrays.asList(2, THREE, FOUR, FIVE),
                Arrays.asList(TWENTY_TWO, THIRTY_THREE, FORTY_FOUR, FIFTY_FIVE),
                "changed"));
        expectedMap.put("numbers3", new Item(Arrays.asList(THREE, FOUR, FIVE), null, "deleted"));
        expectedMap.put("numbers4", new Item(Arrays.asList(FOUR, FIVE, SIX), null, "added"));
        expectedMap.put("obj1", new Item(new LinkedHashMap<String, Object>(Map.of(
                "isNested", true,
                "nestedKey", "value")), null, "added"));
        expectedMap.put("setting1", new Item("Some value", "Another value", "changed"));
        expectedMap.put("setting2", new Item(TWO_HUNDRED, THREE_HUNDRED, "changed"));
        expectedMap.put("setting3", new Item(true, "none", "changed"));
    }


    @ParameterizedTest
    @MethodSource("getExpectedMapForDifference")
    void getDiff(Map<String, Object> dataOne, Map<String, Object> dataTwo) {
        assertEquals(Differences.getDiff(dataOne, dataTwo), expectedMap);
    }

    public static Stream<Arguments> getExpectedMapForDifference() {
        return Stream.of(
                arguments(jsonExpectedDataOne, jsonExpectedDataTwo),
                arguments(yamlExpectedDataOne, yamlExpectedDataTwo));
    }
}
