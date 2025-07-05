package homework.task3;

public class MyQueue<T> {

    private Object[] data;
    private int head;
    private int tail;
    private int size;

    public MyQueue() {
        data = new Object[10];
        head = 0;
        tail = 0;
        size = 0;
    }

    public void add(T value) {
        ensureCapacity();
        data[tail] = value;
        tail = (tail + 1) % data.length;
        size++;
    }

    public T peek() {
        if (size == 0) {
            return null;
        }
        return (T) data[head];
    }

    public T poll() {
        if (size == 0) {
            return null;
        }
        T value = (T) data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;
        return value;
    }

    public void clear() {
        for (int i = 0; i < data.length; i++) {
            data[i] = null;
        }
        head = 0;
        tail = 0;
        size = 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            int newCapacity = data.length + 5;
            Object[] newData = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newData[i] = data[(head + i) % data.length];
            }
            data = newData;
            head = 0;
            tail = size;
        }
    }

    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>();
        queue.add("A");
        queue.add("B");
        queue.add("C");

        System.out.println("Size: " + queue.size());
        System.out.println("Peek: " + queue.peek());
        System.out.println("Poll: " + queue.poll());
        System.out.println("Peek after poll: " + queue.peek());
        System.out.println("Size after poll: " + queue.size());

        queue.clear();
        System.out.println("Size after clear: " + queue.size());

        MyQueue<Integer> intQueue = new MyQueue<>();
        intQueue.add(5);
        intQueue.add(10);
        System.out.println("Integer Queue Peek: " + intQueue.peek());
    }
}
