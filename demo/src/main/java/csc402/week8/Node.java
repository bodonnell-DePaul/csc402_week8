package csc402.week8;

public class Node {

    Movie data;
    Node next;
    Node prev;

    public Node(Movie data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public Movie getData() {
        return data;
    }

    public void setData(Movie data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

}
