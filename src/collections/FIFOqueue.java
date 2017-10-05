package collections;


import java.util.Iterator;

/**
 * Created by Amanda Curyło on 21.09.2017.
 */
public class FIFOqueue<T> implements GraphCollection, Iterable<T>{
    private Node first;
    private Node last;
    private int N;

    private class Node {
        Node next;
        T item;
    }

    public void enqueue(T item) {
        Node oldLast = last;
        last = new Node();
        last.next = null;
        last.item = item;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        N++;
    }

    public T dequeue() {
        T item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }
}
