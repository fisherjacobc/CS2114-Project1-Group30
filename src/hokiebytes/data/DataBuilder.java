package hokiebytes.data;

import java.util.ArrayList;

public class DataBuilder<T> {
    ArrayList<T> data;

    public DataBuilder() {
        data = new ArrayList<T>();
    }

    public ArrayList<T> getArrayList() {
        return data;
    }

    public boolean add(T item) {
        if (item == null || data.contains(item)) {
            return false;
        }

        data.add(item);

        return true;
    }

    public boolean update(T oldItem, T updatedItem) {
        int oldItemIndex = data.indexOf(oldItem);
        int updatedItemIndex = data.indexOf(updatedItem);

        if (oldItem == null || updatedItem == null || oldItemIndex < 0 || updatedItemIndex >= 0) {
            return false;
        }

        data.set(oldItemIndex, updatedItem);

        return true;
    }

    public T remove(T item) {
        int itemIndex = data.indexOf(item);

        if (item == null || itemIndex < 0) {
            return null;
        }

        return data.remove(itemIndex);
    }
}
