package fr.istic.vv;

import java.util.Comparator;

public abstract class BinaryHeap<T> {

        Comparator<T> comparator;
        BinaryHeap(Comparator<T> comparator) {
                this.comparator = comparator;
        }

        public abstract T pop();

        public abstract T peek();

        public abstract void push(T element);

        public abstract int count();

}
