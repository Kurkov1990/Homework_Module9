package homework.task2;

public class MyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(tail, null, value);
        if (head == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }

    public T get(int index) {
        checkIndex(index);
        return searchNode(index).getValue();
    }

    public void remove(int index) {
        checkIndex(index);
        Node<T> target = searchNode(index);

        if (target.getPrev() != null) {
            target.getPrev().setNext(target.getNext());
        } else {
            head = target.getNext();
        }

        if (target.getNext() != null) {
            target.getNext().setPrev(target.getPrev());
        } else {
            tail = target.getPrev();
        }

        target.setNext(null);
        target.setPrev(null);
        target.setValue(null);

        size--;
    }

    public void clear() {
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.getNext();
            current.setNext(null);
            current.setPrev(null);
            current.setValue(null);
            current = next;
        }
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    private Node<T> searchNode(int index) {
        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
        }
        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();

        list.add("Ivan");
        list.add("Maria");
        list.add("Olga");
        list.add("Petro");
        list.add("Andrii");
        list.add("Roman");
        list.add("Anton");
        list.add("Ira");

        System.out.println("Size: " + list.size());
        System.out.println("Element at 2: " + list.get(2));

        list.remove(1);
        System.out.println("Element at 2 after remove: " + list.get(2));
        list.clear();
        System.out.println("Size after clear: " + list.size());
    }
}
