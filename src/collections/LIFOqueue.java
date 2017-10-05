package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Amanda Curyło on 21.09.2017.
 */
public class LIFOqueue<T> implements GraphCollection, Iterable<T> {
    private T[] s = (T[]) new Object[1];
    private int N;

    public void push(T item) {
        if(N==s.length)
            resize(s.length*2);
        s[N++] = item;
    }

    public T pop() {
        T item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4)
            resize(s.length/2);
        return item;
    }

    private void resize(int max) {
        T[] temp = (T[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = s[i];
        s = temp;
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
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i>0;
        }

        @Override
        public T next() {
            if(i == 0) throw new NoSuchElementException("");
            return s[--i];
        }
    }
}
