package com.hms.tiny.url.TinyUrlGenerator.cache;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LRUCache {

    class ListNode {
        String key;
        ListNode prev = null;
        ListNode next = null;

        public ListNode(String k) {
            key = k;
        }
    }

    HashMap<String, ListNode> map = new HashMap<>();
    HashMap<String, String> map1 = new HashMap<>();

    ListNode head = null;
    ListNode tail = null;
    int capacity;

    public LRUCache() {
        this.capacity = 10;
        head = new ListNode("0");
        tail = new ListNode("0");

        head.next = tail;
        tail.prev = head;
    }

    public String get(String key) {
        if(map1.containsKey(key)) {
            String value = map1.get(key);
            set(key, value);
            return value;
        } else {
            return null;
        }

    }

    public void set(String key, String value) {
        if(map.containsKey(key)) {
            ListNode node = map.get(key);
            removeNode(node);
        }
        if(capacity == map.size()) {
            removeNode(head.next);
        }
        addAtTail(key, value);
    }

    void removeNode(ListNode A) {
        map1.remove(A.key);
        map.remove(A.key);

        A.prev.next = A.next;
        A.next.prev = A.prev;
    }

    void addAtTail(String key, String value) {
        ListNode newNode = new ListNode(key);
        map.put(key, newNode);
        map1.put(key, value);

        newNode.next = tail;
        newNode.prev = tail.prev;
        newNode.prev.next = newNode;
        tail.prev = newNode;
    }

}
