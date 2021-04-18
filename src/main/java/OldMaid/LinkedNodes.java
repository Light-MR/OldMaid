package OldMaid;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A linked List
 * @param <T> Any obj you want
 * @author Seiji Dominic Bautista
 */
public class LinkedNodes<T> {
    private Node head;
    private Node current;
    private Node tail;

    private int size = 0;

    /**
     * Data wrapper. Holds the data itself and its adjacent objects.
     */
    public class Node {
        private Node next;
        private Node previous;
        private final T data;

        public Node(T data) {
            this.data = data;
        }

        public void selfDestruct() {
            if (next != null)
                next.setPrevious(previous);
            if (previous != null)
                previous.setNext(next);
        }

        public Node getNext() {
            return next;
        }

        public Node getPrevious() {
            return previous;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }
    }

    /**
     * Constructor: Creates a linked list data structure of "nodes". Becomes cyclic if "isCyclic" is true.
     * @param nodes
     * @param isCyclic
     */
    public LinkedNodes(List<T> nodes, boolean isCyclic) {
        //Collections.shuffle(nodes); //Shuffles the list.
        nodes.forEach(this::addNode);
        if (isCyclic) makeCyclic();
    }

    /**
     * Adds an Element to the List.
     * @param data Data
     */
    public void addNode(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            setHead(newNode);
        } else {
            current.setNext(newNode);
            newNode.setPrevious(current);
        }
        setCurrent(newNode);
        setTail(current);
        size++;
    }

//    /**
//     * Adds an Element to the List.
//     * @param data Data
//     */
//    public void addNode(T data) {
//        Node newNode = new Node(data);
//        if (head == null) {
//            setHead(newNode);
//        } else {
//            current.setNext(newNode);
//            newNode.setPrevious(current);
//        }
//        setCurrent(newNode);
//        if (tail == null) {
//            setTail(newNode);
//        }
//        size++;
//    }

    /**
     * Connects the first and last node to make the list cyclic.
     */
    public void makeCyclic() {
        try {
            //make sure that these two are not null.
            head.setPrevious(tail);
            tail.setNext(head);
        } catch(NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Moves the current pointer to the next Node.
     */
    public Node next() {
        if (current.getNext() != null)
            setCurrent(current.getNext());
        else
            System.out.println("End of list reached");
        return current;
    }

    /**
     * Moves the current pointer to the previous node.
     */
    public Node previous() {
        if (current.getPrevious() != null) {
            setCurrent(current.getPrevious());
        } else {
            System.out.println("Start of list reached");
        }
        return current;
    }

    /**
     * Gets the current data from the Node wrapper.
     * @return Current Node's data
     */
    public T getCurrentData() {
        return current.getData();
    }

    /**
     * Retrieves the current Node's previous Node's data
     * @return Previous Node's data
     */
    public T getPreviousData() {
        return current.getPrevious().getData();
    }

    /**
     * Retrieves the current's Node's next Node's data
     * @return Next Node's data
     */
    public T getNextData() {
        return current.getNext().getData();
    }

    public void destroyCurrent() {
        Node temp = current;
        current = current.getNext();
        temp.selfDestruct();
        size--;
    }

    public Node getCurrent() {
        return current;
    }

    public Node getHead() {
        return head;
    }

    public int size() {
        return this.size;
    }

    public Node getTail() {
        return tail;
    }

    /**
     * Sets the "current pointer".
     * @param current Node
     */
    public void setCurrent(Node current) {
        this.current = current;
    }

    /**
     * Sets the first element pointer.
     * @param head Node
     */
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * Sets the last element's pointer.
     * @param tail Node
     */
    public void setTail(Node tail) {
        this.tail = tail;
    }
}

