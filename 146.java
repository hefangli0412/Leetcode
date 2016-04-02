// https://leetcode.com/problems/lru-cache/

public class LRUCache {
    private final int capacity;
    private Node head;
    private Node tail;
    private Map<Integer, Node> map;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = tail = null;
        map = new HashMap<Integer, Node>();
    }
    
    // Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        
        remove(node);
        appendToHead(node);
        
        return node.value;
    }
    
    // Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
    public void set(int key, int value) {
        Node node = map.get(key);
        
        if (node != null) {
            remove(node);
        } else if (map.size() < capacity) {
            node = new Node(key, value);
        } else {
            node = tail;
            remove(node);
        }
        
        node.update(key, value); // must update after remove, map operation is involved
        appendToHead(node);
    }
    
    // node is not null
    private void remove(Node node) {
        map.remove(node.key);
        
        Node prev = node.prev;
        Node next = node.next;
        if (prev != null) {
            prev.next = node.next;
        }
        if (next != null) {
            next.prev = node.prev;
        }
        
        if (node == head) {
            head = next;
        }
        if (node == tail) {
            tail = prev;
        }
        
        node.next = node.prev = null;
    }
    
    // delete tail first if size may exceed the capacity
    private void appendToHead(Node node) {
        map.put(node.key, node);
        
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }
}

// DLL
class Node {
    int key; // used for convenience of map operations
    int value;
    Node prev;
    Node next;
    
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
    
    public void update(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
