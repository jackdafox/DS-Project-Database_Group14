

import java.util.Iterator;

import Types.ValueFields;
/**
 *
 * @dzxky_
 */
public class DatabaseMap<K, V extends ValueFields> {

    private static class Node<K, V> {

        K key;
        V value;
        Node<K, V> nextNode;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }
    }

    private int capacity;
    private float loadFactor;
    private int size;
    private Node<K, V>[] map;
    private HashList<K> hashList;

    public DatabaseMap() {
        this(16, 0.75f);
    }

    public DatabaseMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.map = new Node[capacity];
        this.hashList = new HashList<>();
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value) {
        int index = hash(key);
        hashList.add(key);
        Node<K, V> node = map[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.nextNode;
        }
        Node<K, V> newNode = new Node<>(key, value);
        newNode.nextNode = map[index];
        map[index] = newNode;
        size++;
        if (size > capacity * loadFactor) {
            resize();
        }
    }
    
    public boolean set(K key, V value) {
        int index = hash(key);
        Node<K, V> node = map[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return true;
            }
            node = node.nextNode;
        }
        return false;
    }

    public V get(K key) {
        int index = hash(key);
        Node<K, V> node = map[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.nextNode;
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        hashList.remove(key);
        Node<K, V> node = map[index];
        Node<K, V> prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    map[index] = node.nextNode;
                } else {
                    prev.nextNode = node.nextNode;
                }
                size--;
                return;
            }
            prev = node;
            node = node.nextNode;
        }
    }
    
    public void clear() {
    	map = new Node[capacity];
    	hashList = new HashList<>();
    }

    public void resize() {
        int newCapacity = capacity * 2;
        Node<K, V>[] newMap = new Node[newCapacity];
        for (int i = 0; i < capacity; i++) {
            Node<K, V> node = map[i];
            while (node != null) {
                int newIndex = hash(node.key);
                newMap[newIndex] = new Node<>(node.key, node.value);
                newMap[newIndex].nextNode = newMap[newIndex];
                node = node.nextNode;
            }
        }
        map = newMap;
        capacity = newCapacity;
    }

    public boolean containsKey(K key) {
        int index = hash(key);
        Node<K, V> node = map[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return true;
            }
            node = node.nextNode;
        }
        return false;
    }

    public HashList<ValueFields> getMany() {
        HashList<ValueFields> valueList = new HashList<>();
        for (K key : hashList) {
            valueList.add(get(key));
        }
        return valueList;
    }
    
    public HashList<K> getKeys() {
    	if(hashList.isEmpty()) {
    		return null;
    	}
        return hashList;
    }
}
