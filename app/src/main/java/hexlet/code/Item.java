package hexlet.code;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Item {

    public static final String ADDED = "added";
    public static final String DELETED = "deleted";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private final Object oldValue;
    private final Object newValue;
    private final String status;

    public Item(Object inputOldValue, String inputStatus) {
        this.oldValue = inputOldValue;
        this.status = inputStatus;
        this.newValue = null;
    }

}

