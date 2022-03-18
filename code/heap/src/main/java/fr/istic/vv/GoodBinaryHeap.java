package fr.istic.vv;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class GoodBinaryHeap<T> extends BinaryHeap<T>{

    ArrayList<T> heap;
    int nextPos;

    GoodBinaryHeap(Comparator<T> comparator) {
        super(comparator);
        heap = new ArrayList<>();
        nextPos = 0;
    }

    @Override
    public T pop() {

        return null;
    }

    int getParent(int node) {
        return (node-1)/2;
    }

    int getChildIndex(int node,int child) {
        return node*2+child;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public void push(T element) {
        heap.add(nextPos,element);
        boolean correct = false;
        int currentPos = nextPos;
        while (true) {
            int parent = getParent(currentPos);
            if (currentPos == 0) {
                return ;
            }
            if (comparator.compare(heap.get(currentPos),heap.get(parent)) < 0) {
                T tmp = heap.get(currentPos);
                heap.set(currentPos,heap.get(parent));
                heap.set(parent,tmp);
                currentPos = parent;
            }
            else {
                return ;
            }
        }

    }


    @Override
    public int count() {
        return 0;
    }
}
