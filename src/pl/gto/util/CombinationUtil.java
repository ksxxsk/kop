package pl.gto.util;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 12/1/2016.
 */
public class CombinationUtil {

    /*
     * arr[] ---> Input Array data[] ---> Temporary array to store current combination start & end ---> Staring and Ending indexes in arr[] index ---> Current
     * index in data[] r ---> Size of a combination to be printed
     */
    private static void combinationUtil(List<int[]> result, int arr[], int data[], int start, int end, int index, int r) {
        // Current combination is ready to be printed, print it
        if (index == r) {
            result.add(Arrays.copyOf(data, data.length));
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            combinationUtil(result, arr, data, i + 1, end, index + 1, r);
        }
    }

    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    public static void addCombination(List<int[]> result, int arr[], int n, int r) {
        // A temporary array to store all combination one by one
        int data[] = new int[r];

        // Print all combination using temprary array 'data[]'
        combinationUtil(result, arr, data, 0, n - 1, 0, r);
    }

}
