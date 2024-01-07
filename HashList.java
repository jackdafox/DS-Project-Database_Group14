
import java.util.Iterator;

/**
 *
 * @dzxky_
 */
public class HashList<E> implements Iterable<E> {

    private E[] array;
    private int size;

    public class HashListIterator implements Iterator<E> {

        int cursor;
        int lastReturned = -1;

        public boolean hasNext() {
            return cursor != size;
        }

        public E next() {
            return getNextElement();
        }

        private E getNextElement() {
            int current = cursor;

            E[] elements = HashList.this.array;

            cursor = current + 1;
            lastReturned = current;
            return elements[lastReturned];
        }
    }

    public HashList() {
        array = (E[]) new Object[1];
        size = 0;
    }

    public void add(E element) {
        if (size == array.length) {
            resizeArray();
        }
        array[size++] = element;
    }

    public boolean remove(E item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                size--;
                return true;
            }
        }
        return false;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public int size() {
        return size;
    }
    
    public void clear() {
        array = (E[]) new Object[array.length];
        size = 0;
    }

    private void resizeArray() {
        E[] newArray = (E[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    @Override
    public Iterator<E> iterator() {
        return new HashListIterator();
    }
    
    public String toString() {
        String output = "";
        for (E e : array) {
            output += e + " ";
        }
        return output;
    }

}
