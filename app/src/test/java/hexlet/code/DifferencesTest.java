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
        int fourFive = 45;
        int twentyTwo = 22;
        int three = 3;
        int thirtyThree = 33;
        int four = 4;
        int fortyFour = 44;
        int fiftyFive = 55;
        int five = 5;
        int six = 6;
        int twoHundred = 200;
        int threeHundred = 300;
        expectedMap.put("chars1", new Item(Arrays.asList("a", "b", "c"), Arrays.asList("a", "b", "c"), "unchanged"));
        expectedMap.put("chars2", new Item(Arrays.asList("d", "e", "f"), false, "changed"));
        expectedMap.put("checked", new Item(false, true, "changed"));
        expectedMap.put("default", new Item(null, Arrays.asList("value1", "value2"), "changed"));
        expectedMap.put("id", new Item(fourFive, null, "changed"));
        expectedMap.put("key1", new Item("value1", null, "deleted"));
        expectedMap.put("key2", new Item("value2", null, "added"));
        expectedMap.put("numbers1", new Item(
                Arrays.asList(1, 2, three, four),
                Arrays.asList(1, 2, three, four),
                "unchanged"));
        expectedMap.put("numbers2", new Item(
                Arrays.asList(2, three, four, five),
                Arrays.asList(twentyTwo, thirtyThree, fortyFour, fiftyFive),
                "changed"));
        expectedMap.put("numbers3", new Item(Arrays.asList(three, four, five), null, "deleted"));
        expectedMap.put("numbers4", new Item(Arrays.asList(four, five, six), null, "added"));
        expectedMap.put("obj1", new Item(new LinkedHashMap<String, Object>(Map.of(
                "isNested", true,
                "nestedKey", "value")), null, "added"));
        expectedMap.put("setting1", new Item("Some value", "Another value", "changed"));
        expectedMap.put("setting2", new Item(twoHundred, threeHundred, "changed"));
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
