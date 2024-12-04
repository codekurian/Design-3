import java.util.*;

//TC:O(N) where n is the capacity
//SC: O(N)
class LRUCache {
    Node head ;
    Node tail ;
    int capacity;
    Map<Integer,Node> cache;
    class Node{
        private int key,value;
        private Node pre,next;
        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);

        head.next = tail;
        tail.pre = head;


    }

    public int get(int key) {
        if(!this.cache.containsKey(key)){
            return -1;
        }else{
            Node toBeRemoved = this.cache.get(key);
            deletedNode(toBeRemoved);
            this.cache.remove(key);
            Node newNode = addNodeToHead(toBeRemoved.key,toBeRemoved.value);
            this.cache.put(key,newNode);
            return this.cache.get(key).value;
        }
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            deletedNode(node);
            this.cache.remove(node.key);
        }else if(this.cache.size() == this.capacity){
            Node removed = removeTail();
            this.cache.remove(removed.key);
        }
        Node newNode = addNodeToHead(key,value);
        this.cache.put(key,newNode);
    }

    public void deletedNode(Node node) {
        Node curr = node;
        curr.pre.next = curr.next;
        curr.next.pre =  curr.pre;
        curr.next = null;
        curr.pre = null;
    }

    public Node addNodeToHead(int key,int value) {
        Node n = new Node(key,value);
        Node curr = head.next;
        n.next = curr;
        curr.pre = n ;
        n.pre = head;
        head.next = n;
        return n;
    }


    public Node removeTail() {
        Node curr = this.tail.pre;
        curr.pre.next = tail;
        tail.pre = curr.pre;
        return curr;
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */