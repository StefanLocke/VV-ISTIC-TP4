package fr.istic.vv;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {
        int swaps = 0;
        do {
            swaps = 0;
            for (int i = 0; i < array.length-1; i++){
                if (comparator.compare(array[i],array[i+1]) > 0) {
                    swap(array,i,i+1);
                    swaps++;
                }
            }
        } while (swaps > 0);
        return array;
    }

    /*---QUICKSORT-------*/
    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  {
        quickSort(array,comparator,0,array.length-1);
        return array; }

    private static <T> void quickSort(T[] array, Comparator<T> comaparator, int low, int high) {
        if (low < high)
        {
        /* pi is partitioning index, arr[pi] is now
           at right place */
            int pi = partition(array,comaparator, low, high);

            quickSort(array,comaparator, low, pi - 1);  // Before pi
            quickSort(array,comaparator, pi + 1, high); // After pi
        }
    }

    private static <T> int partition(T[] array, Comparator<T> comaparator, int low, int high)
    {
        // pivot (Element to be placed at right position)
        T pivot = array[high];

        int i = (low - 1);  // Index of smaller element and indicates the
        // right position of pivot found so far

        for (int j = low; j <= high-1; j++)
        {
            // If current element is smaller than the pivot
            if (comaparator.compare(array[j],pivot) < 0)
            {
                i++;    // increment index of smaller element
                swap(array,i,j);
            }
        }
        swap(array,i + 1,high);
        return (i + 1);
    }
    /*---MERGESORT-------*/
    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        sort(array,comparator,0,array.length-1);
        return array;

    }

    public static <T> void sort(T[] array,Comparator<T> comparator, int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m =l+ (r-l)/2;

            // Sort first and second halves
            sort(array,comparator, l, m);
            sort(array, comparator,m + 1, r);

            // Merge the sorted halves
            merge(array,comparator, l, m, r);
        }
    }

    private static <T> void merge(T[] array,Comparator<T> comparator, int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        ArrayList<T> L = new ArrayList<>();
        ArrayList<T> R = new ArrayList<T>();

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L.add(i,array[l + i]);
        for (int j = 0; j < n2; ++j)
            R.add(j,array[m + 1 + j]);

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {

            if (comparator.compare(L.get(i),R.get(j)) <= 0) {
                array[k] = L.get(i);
                i++;
            }
            else {
                array[k] = R.get(j);
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            array[k] = L.get(i);
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            array[k] = R.get(j);
            j++;
            k++;
        }
    }



    private static <T> void swap(T[] array, int i, int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
