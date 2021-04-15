package OldMaid;

import java.util.Collections;
import java.util.List;

/**
 * A linked List
 * @param <T> Any obj you want
 * @author Dominic
 */
public class LinkedNodes<T> {
    private Node head;
    private Node tail;
    private Node current;

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

    public LinkedNodes(List<T> nodes, boolean isCyclic) {
        Collections.shuffle(nodes);
        nodes.forEach(this::addNode);
        if (isCyclic) makeCyclic();
    }

    public void addNode(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            setHead(newNode);
        } else {
            current.setNext(newNode);
            newNode.setPrevious(current);
            setTail(newNode);
        }
        setCurrent(newNode);
    }

    public void makeCyclic() {
        try {
            //make sure that these two are not null.
            head.setPrevious(tail);
            tail.setNext(head);
        } catch(NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void next() {
        if (current.getNext() != null)
            setCurrent(current.getNext());
        else
            System.out.println("End of list reached");
    }

    public void previous() {
        if (current.getPrevious() != null) {
            setCurrent(current.getPrevious());
        } else {
            System.out.println("Start of list reached");
        }
    }

    public T getCurrentData() {
        return current.getData();
    }

    public T getPreviousData() {
        return current.getPrevious().getData();
    }

    public T getNextData() {
        return current.getNext().getData();
    }

    public Node getCurrent() {
        return current;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public void setCurrent(Node current) {
        this.current = current;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }
}

