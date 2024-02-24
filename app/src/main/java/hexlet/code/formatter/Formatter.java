package hexlet.code.formatter;

import hexlet.code.Item;

import java.util.Map;

public class Formatter {
    private static final String PLAIN_FORMAT = "plain";
    private static final String JSON_FORMAT = "json";
    private static final String STYLISH_FORMAT = "stylish";

    public static String getFormat(Map<String, Item> differ, String format) throws Exception {
        return switch (format) {
            case PLAIN_FORMAT -> Plain.makePlain(differ);
            case JSON_FORMAT -> Json.makeJson(differ);
            case STYLISH_FORMAT -> Stylish.makeStylish(differ);
            default -> throw new Exception("Formatting error");
        };
    }
}
