package org.personal.hashmap;

public class CustomHashMap<K, V> {
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

    }

    private Node<K, V>[] buckets;
    private int capacity = 16;
    private int size = 0;

    CustomHashMap() {
        buckets = new Node[capacity];
    }

    // get()

    // put()

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % size;
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> head = buckets[index];

        while(head != null) {
            if(head.key.equals(key))
                return head.value;
            head = head.next;
        }
        return null;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> head = buckets[index];
        if(head == null)
            buckets[index] = new Node<>(key, value);
        else {
            Node<K, V> prev = null;
            while(head != null) {
                if(head.value.equals(value)) {
                    head.value = value; // update and move
                    return;
                }
                prev = head;
                head = head.next;
            }
            prev.next = new Node<>(key, value);
        }
        size++;
    }

}
