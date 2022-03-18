package fr.istic.vv;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BadBinaryHeap<T> {

    private LinkedList<T> list;
    private Comparator<T> comparator;

    public BadBinaryHeap(Comparator<T> comparator) {
        list = new LinkedList<>();
        this.comparator = comparator;
    }

    public T pop() {
        if (list.isEmpty()) throw new NoSuchElementException();
        T min = list.stream().min(comparator).get();
        list.remove(min);
        return min; }

    public T peek() {
        if (list.isEmpty()) throw  new NoSuchElementException();
        T min = list.stream().min(comparator).get();
        return min;
    }

    public void push(T element) {
        list.add(element);
    }

    public int count() {
        return list.size();
    }

}