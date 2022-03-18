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
//        System.out.println("POPPING");
//        System.out.println(toString());
        if (heap.size() == 0) throw new NoSuchElementException();
        T value = heap.get(0);
        if (heap.size() > 1) {
            T newRoot = heap.remove(heap.size() - 1);
            heap.set(0, newRoot);
            System.out.println("Prefix");
            System.out.println(this);
            fixHeap(0);
            System.out.println("PostFix");
            System.out.println(this);
        }
        else {
            heap.clear();
        }
//        System.out.println(value);
//        System.out.println(toString());
        return value;
    }

    private void fixHeap(int root){
        int leftChildIndex = getChildIndex(root,1);
        int rightChildIndex = getChildIndex(root,2);
        if (!(leftChildIndex >= heap.size()))  fixHeap(leftChildIndex);
        if (!(rightChildIndex >= heap.size()))  fixHeap(rightChildIndex);
        while (!checkCorrect(root)) {
            if (!checkRightCorrect(root)) {
                swap(rightChildIndex,root);
            }
            if (!checkLeftCorrect(root)) {
                swap(leftChildIndex,root);
            }
        }
    }

    private boolean checkCorrect(int parentIndex){
        return checkLeftCorrect(parentIndex) && checkRightCorrect(parentIndex);
    }

    private boolean checkLeftCorrect(int parentIndex){
        int leftChildIndex = getChildIndex(parentIndex,1);
        if (leftChildIndex >= heap.size()) return true;
        return !(comparator.compare(heap.get(leftChildIndex),heap.get(parentIndex)) < 0);
    }
    private boolean checkRightCorrect(int parentIndex){
        int rightChildIndex = getChildIndex(parentIndex,2);
        if (rightChildIndex >= heap.size()) return true;
        return !(comparator.compare(heap.get(rightChildIndex),heap.get(parentIndex)) < 0);
    }

    private void swap(int index1,int index2){
        T tmp = heap.get(index1);
        heap.set(index1,heap.get(index2));
        heap.set(index2,tmp);
    }

    private int getParent(int node) {
        return (node-1)/2;
    }

    private int getChildIndex(int node,int child) {
        return node*2+child;
    }

    @Override
    public T peek() {
        if (heap.size() == 0) throw new NoSuchElementException();
        return heap.get(0);
    }

    @Override
    public void push(T element) {
        heap.add(element);
        int currentPos = heap.size()-1;
        while (true) {
            int parent = getParent(currentPos);
            if (currentPos == 0) {
                return ;
            }
            if (comparator.compare(heap.get(currentPos),heap.get(parent)) < 0) {
                swap(currentPos,parent);
                currentPos = parent;
            }
            else {
                return ;
            }
        }

    }


    @Override
    public int count() {
        return heap.size();
    }

    @Override
    public String toString() {
        return "GoodBinaryHeap{" +
                "heap=" + heap +
                '}';
    }
}
