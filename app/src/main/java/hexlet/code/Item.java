package hexlet.code;


import java.util.Objects;

public final class Item {

    public static final String ADDED = "added";
    public static final String DELETED = "deleted";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private final Object oldValue;
    private final Object newValue;
    private final String status;

    public Item(Object inputOldValue, Object inputNewValue, String inputStatus) {

        this.oldValue = inputOldValue;
        this.newValue = inputNewValue;
        this.status = inputStatus;

    }

    public Item(Object inputOdValue, String inputStatus) {

        this.oldValue = inputOdValue;
        this.status = inputStatus;
        this.newValue = null;

    }


    public Object getNewValue() {

        return this.newValue;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(oldValue, item.oldValue)
                && Objects.equals(newValue, item.newValue)
                && Objects.equals(status, item.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oldValue, newValue, status);
    }

    public Object getOldValue() {

        return this.oldValue;

    }

    public String getStatus() {

        return this.status;

    }

    @Override
    public String toString() {
        return "Item{"
                + "oldValue=" + oldValue
                + ", newValue="
                + newValue
                + ", status='" + status + '\''
                + '}';
    }
}
