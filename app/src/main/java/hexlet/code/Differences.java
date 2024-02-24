package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static hexlet.code.Item.ADDED;
import static hexlet.code.Item.CHANGED;
import static hexlet.code.Item.DELETED;
import static hexlet.code.Item.UNCHANGED;

public class Differences {

    public static Map<String, Item> getDiff(Map<String, Object> dataFileOne, Map<String, Object> dataFileTwo) {
        Map<String, Item> differ = new TreeMap<>();

        Set<String> allKey = new TreeSet<>();
        allKey.addAll(dataFileOne.keySet());
        allKey.addAll(dataFileTwo.keySet());
        for (String key : allKey) {
            Object oldValue = dataFileOne.get(key);
            Object newValue = dataFileTwo.get(key);
            if (!dataFileOne.containsKey(key)) {
                differ.put(key, new Item(newValue, ADDED));
            } else if (!dataFileTwo.containsKey(key)) {
                differ.put(key, new Item(oldValue, DELETED));
            } else if (Objects.equals(oldValue, newValue)) {
                differ.put(key, new Item(oldValue, newValue, UNCHANGED));
            } else {
                differ.put(key, new Item(oldValue, newValue, CHANGED));
            }
        }
        return differ;
    }
}
