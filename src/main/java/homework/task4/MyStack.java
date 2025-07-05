package homework.task4;

public class MyStack<T> {

    private Object[] data;
    private int size;

    public MyStack() {
        data = new Object[10];
        size = 0;
    }

    public void push(T value) {
        ensureCapacity();
        data[size++] = value;
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

    public T peek() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return (T) data[size - 1];
    }

    public T pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        T top = (T) data[size - 1];
        data[size - 1] = null;
        size--;
        return top;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            Object[] newData = new Object[data.length + 5];
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
        MyStack<String> stack = new MyStack<>();

        stack.push("Apple");
        stack.push("Banana");
        stack.push("Peach");
        stack.push("Orange");
        stack.push("Grape");

        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.size());

        stack.remove(0);
        System.out.println(stack.peek());

        stack.clear();
        System.out.println(stack.size());
    }
}
