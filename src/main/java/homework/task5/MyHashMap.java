package homework.task5;

public class MyHashMap<K, V> {

    private Node<K, V>[] buckets;
    private int size;
    private int capacity;
    private final double loadFactor;


    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this.capacity = 16;
        this.loadFactor = 0.75;
        this.buckets = (Node<K, V>[]) new Node[16];
        size = 0;
    }

    private int getBucketIndex(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode() % capacity);
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];

        while (current != null) {
            if ((key == null && current.getKey() == null) || (key != null && key.equals(current.getKey()))) {
                current.setValue(value);
                return;
            }
            current = current.getNext();
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.setNext(buckets[index]);
        buckets[index] = newNode;
        size++;

        if ((double) size / capacity > loadFactor) {
            resize();
        }
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];

        while (current != null) {
            if ((key == null && current.getKey() == null) || (key != null && key.equals(current.getKey()))) {
                return current.getValue();
            }
            current = current.getNext();
        }

        return null;
    }

    public boolean remove(K key) {
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];
        Node<K, V> prev = null;

        while (current != null) {
            if ((key == null && current.getKey() == null) || (key != null && key.equals(current.getKey()))) {
                if (prev == null) {
                    buckets[index] = current.getNext();
                } else {
                    prev.setNext(current.getNext());
                }
                size--;
                return true;
            }
            prev = current;
            current = current.getNext();
        }

        return false;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        Node<K, V>[] newBuckets = (Node<K, V>[]) new Node[newCapacity];

        for (int i = 0; i < capacity; i++) {
            Node<K, V> current = buckets[i];
            while (current != null) {
                Node<K, V> next = current.getNext();

                int newIndex = (current.getKey() == null) ? 0 : Math.abs(current.getKey().hashCode() % newCapacity);
                current.setNext(newBuckets[newIndex]);
                newBuckets[newIndex] = current;

                current = next;
            }
        }

        this.buckets = newBuckets;
        this.capacity = newCapacity;
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("key1", 5);
        map.put("key2", 21);
        map.put("key3", 30);
        map.put("key4", 56);
        map.put("key5", 24);
        map.put("key6", 33);

        System.out.println("key1 = " + map.get("key1"));
        System.out.println("key3 = " + map.get("key3"));
        System.out.println("key7 = " + map.get("key7"));

        map.put("key1", -11);
        System.out.println("key1 (updated) = " + map.get("key1"));

        System.out.println("Size = " + map.size());

        boolean removed = map.remove("key3");
        System.out.println("key3 id removed ? " + removed);
        System.out.println("key3 = " + map.get("key3"));
        System.out.println("Size after removal = " + map.size());


        map.clear();
        System.out.println("Size after clear = " + map.size());
    }
}
