package fr.istic.vv;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {

        int swaps = 0;
        do {
            for (int i = 0; i < array.length-1; i++){
                if (comparator.compare(array[i],array[i+1]) < 0) {
                    swap(array,i,i+1);
                    swaps++;
                }
            }
        } while (swaps != 0);
        return array; }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  { return null; }




    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) { return null; }



    private static <T> void swap(T[] array, int i, int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
