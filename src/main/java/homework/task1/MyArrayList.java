package homework.task1;

public class MyArrayList<T> {

    private Object[] data;
    private int size;

    public MyArrayList() {
        data = new Object[2];
        size = 0;
    }

    public void add(T value) {
        ensureCapacity();
        data[size] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            int newCapacity = data.length + 5;
            Object[] newData = new Object[newCapacity];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        System.out.println("Size before adding elements: " + list.size());
        list.add("Java");
        list.add("Ruby");
        list.add("C++");
        list.add("Python");

        System.out.println("Size: " + list.size());
        System.out.println("Item at 1: " + list.get(1));

        list.remove(1);
        System.out.println("Size after remove: " + list.size());
        System.out.println("Item with index 1: " + list.get(1));

        list.clear();
        System.out.println("Size after clear: " + list.size());
    }
}
