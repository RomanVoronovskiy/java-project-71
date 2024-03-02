package hexlet.code;

import hexlet.code.formatter.Formatter;

import java.util.Map;

public class Differ {

    private static final String DEFAULT = "stylish";

    public static String generate(String firstFilePath, String secondFilePath, String format) throws Exception {
        String content1 = FileUtils.readFile(firstFilePath);
        String contain2 = FileUtils.readFile(secondFilePath);
        String dataFormat1 = FileUtils.getDataFormat(firstFilePath);
        String dataFormat2 = FileUtils.getDataFormat(secondFilePath);
        Map<String, Object> data1 = Parser.getData(content1, dataFormat1);
        Map<String, Object> data2 = Parser.getData(contain2, dataFormat2);
        Map<String, Item> differ = Differences.getDiff(data1, data2);
        return Formatter.getFormat(differ, format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, DEFAULT);
    }
}
