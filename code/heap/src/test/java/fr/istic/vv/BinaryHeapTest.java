package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.PerProperty;
import net.jqwik.api.lifecycle.PropertyExecutionResult;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;


public class BinaryHeapTest {
    Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer integer, Integer t1) {
            if (integer instanceof Integer && t1 instanceof  Integer) {
                if ((((Integer) integer).intValue() < ((Integer) t1).intValue())) return -1;
                if (((Integer) integer).intValue() > ((Integer) t1).intValue()) return 1;
            }
            return 0;
        }
    };



    @Property
    @Label("Ensures the peek method returns the minimum value in the heap")
    boolean testBinaryHeapMinPeekProperty(@ForAll("getIntegerList")List<Integer> list) {
        BadBinaryHeap<Integer> heap = new BadBinaryHeap<>(comparator);
        Integer min = null;
        for (Integer value : list) {
            if (min == null) min = value;
            if (comparator.compare(min,value) != -1) min = value;
            heap.push(value);
        }

        return comparator.compare(heap.peek(),min) == 0;
    }
    @Property
    @Label("Ensures the pop method returns the minimum value from the heap")
    boolean testBinaryHeapMinPopProperty(@ForAll("getIntegerList")List<Integer> list) {
        BadBinaryHeap<Integer> heap = new BadBinaryHeap<>(comparator);
        Integer min = null;
        for (Integer value : list) {
            if (min == null) min = value;
            if (comparator.compare(min,value) != -1) min = value;
            heap.push(value);
        }

        return comparator.compare(heap.pop(),min) == 0;
    }

    @Property
    @Label("Ensures the pop methods removes the returned value from the heap")
    boolean testBinaryHeapRemovedOnPopProperty(@ForAll("getIntegerList")List<Integer> list) {
        BadBinaryHeap<Integer> heap = new BadBinaryHeap<>(comparator);
        for (Integer value : list) {
            heap.push(value);
        }
        Integer initialSize = heap.count();
        heap.pop();
        Integer newSize = heap.count();
        return comparator.compare(newSize,initialSize) < 0;
    }

    @Property(tries = 1)
    @Label("Ensures doing a pop on a empty heap returns a NoSuchElementException exception")
    @PerProperty(SucceedIfThrowsNoSuchElementException.class)
    boolean testEmptyHeapPropertyPop() {
        BadBinaryHeap<Integer> heap = new BadBinaryHeap<>(comparator);
        heap.pop();
        return true;
    }

    @Property(tries = 1)
    @Label("Ensures doing a peek on a empty heap returns a NoSuchElementException exception")
    @PerProperty(SucceedIfThrowsNoSuchElementException.class)
    boolean testEmptyHeapPropertyPeek() {
        BadBinaryHeap<Integer> heap = new BadBinaryHeap<>(comparator);
        heap.peek();
        return true;
    }

    private class SucceedIfThrowsNoSuchElementException implements PerProperty.Lifecycle {
        @Override
        public PropertyExecutionResult onFailure(PropertyExecutionResult propertyExecutionResult) {
            if (propertyExecutionResult.throwable().isPresent() &&
                    propertyExecutionResult.throwable().get() instanceof NoSuchElementException) {
                return propertyExecutionResult.mapToSuccessful();
            }
            return propertyExecutionResult;
        }
    }

    @Provide
    @Label("Provides a list of Integers with up to 1000 elements")
    Arbitrary<List<Integer>> getIntegerList() {
        return Arbitraries.integers().list().ofMaxSize(1000).ofMinSize(1);
    };
}
