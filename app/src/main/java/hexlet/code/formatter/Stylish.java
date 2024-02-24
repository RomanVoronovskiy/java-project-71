package hexlet.code.formatter;

import hexlet.code.Item;

import java.util.Map;

import static hexlet.code.Item.ADDED;
import static hexlet.code.Item.CHANGED;
import static hexlet.code.Item.DELETED;
import static hexlet.code.Item.UNCHANGED;

public class Stylish {

    public static String makeStylish(Map<String, Item> differ) throws Exception {
        StringBuilder result = new StringBuilder();
        result.append("{");
        for (Map.Entry<String, Item> item : differ.entrySet()) {
            result.append("\n").append(" ".repeat(2));
            switch (item.getValue().getStatus()) {
                case ADDED:
                    result.append("+").append(" ")
                            .append(item.getKey()).append(": ").append(item.getValue().getOldValue());
                    break;
                case DELETED:
                    result.append("-").append(" ")
                            .append(item.getKey()).append(": ").append(item.getValue().getOldValue());
                    break;
                case CHANGED:
                    result.append("-").append(" ")
                            .append(item.getKey()).append(": ").append(item.getValue().getOldValue());
                    result.append("\n").append(" ".repeat(2)).append("+").append(" ")
                            .append(item.getKey()).append(": ").append(item.getValue().getNewValue());
                    break;
                case UNCHANGED:
                    result.append(" ".repeat(2))
                            .append(item.getKey()).append(": ").append(item.getValue().getOldValue());
                    break;
                default:
                    throw new Exception("Incorrect status: '" + item.getValue().getStatus() + "'");
            }
        }
        return result.append("\n}").toString();
    }
}
