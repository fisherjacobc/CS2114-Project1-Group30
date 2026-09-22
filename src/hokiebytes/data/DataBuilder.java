package hokiebytes.data;

import java.util.ArrayList;

/**
 * DataBuilder Class
 * 
 * <p>
 * Wrapper class for generic typed data
 * 
 * @author Jacob Fisher (fisherjc)
 * @version 2026.09.18
 */
public class DataBuilder<T> {
    ArrayList<T> data;

    /**
     * Initializes a new DataBuilder class
     */
    public DataBuilder() {
        data = new ArrayList<T>();
    }

    /**
     * Return the ArrayList
     * 
     * @return {@link ArrayList} - the Array List
     */
    public ArrayList<T> getArrayList() {
        return data;
    }

    /**
     * Add a [unique] item to the ArrayList
     * 
     * @param item the item to add
     * @return true if it was able to add it (isn't null, and doesn't already exist
     *         in the ArrayList)
     */
    public boolean add(T item) {
        if (item == null || data.contains(item)) {
            return false;
        }

        data.add(item);

        return true;
    }

    /**
     * Update an item in the ArrayList
     * 
     * @param oldItem
     * @param updatedItem
     * @return true if the old and new item aren't null, the old item exists, and
     *         the new item doesn't already exist.
     */
    public boolean update(T oldItem, T updatedItem) {
        int oldItemIndex = data.indexOf(oldItem);
        int updatedItemIndex = data.indexOf(updatedItem);

        if (oldItem == null || updatedItem == null || oldItemIndex < 0 || updatedItemIndex >= 0) {
            return false;
        }

        data.set(oldItemIndex, updatedItem);

        return true;
    }

    /**
     * Remove an item from the ArrayList
     * 
     * @param item the item to remove
     * @return the removed item
     */
    public T remove(T item) {
        int itemIndex = data.indexOf(item);

        if (item == null || itemIndex < 0) {
            return null;
        }

        return data.remove(itemIndex);
    }
}
