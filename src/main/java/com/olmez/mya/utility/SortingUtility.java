package com.olmez.mya.utility;

import lombok.experimental.UtilityClass;

/**
 * It is used to sort very large arrays consisting of integers or chars.
 * <p>
 * Arrays.sort(arr) or Arrays.sort(arr, Collections.reverseOrder()) for
 * non-large arrays
 * </p>
 * <p>
 * Collections.sort(list) or Collections.sort(list, Collections.reverseOrder())
 * is used.
 * </p>
 */
@UtilityClass
public class SortingUtility {

    /**
     * 1.COUNTING SORTING
     * ////////////////////////////////////////////////////////////////////////////////////
     * <p>
     * First, this creates an array of frequency values of numbers. Then get back
     * the numbers from this array by the number of frequencies.
     * </p>
     * 
     * @param array list of integer
     * @return sorted list
     */
    public static int[] countSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int[] fre = createFrequencyArray(arr);
        return sort(fre, arr);
    }

    /**
     * Find how many of each number (frequency). Place frequency values in the
     * relevant index in the new array. For example, if you have nine "number of 5",
     * it will be showing in the array like frequency[5]=9.
     * 
     * @param arr numbers
     * @return array of frequency
     */
    private int[] createFrequencyArray(int[] arr) {
        int max = getMax(arr);
        int[] fre = new int[max + 1];
        for (int num : arr) {
            fre[num]++;
        }
        return fre;
    }

    private int[] sort(int[] fre, int[] arr) {
        int[] sorted = new int[arr.length];
        int index = 0;
        for (int i = 0; i < fre.length; i++) {
            for (int j = 0; j < fre[i]; j++) {
                sorted[index] = i;
                index++;
            }

        }
        return sorted;
    }

    private int getMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    // for char array
    public static char[] countSort(char[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        char[] freArray = new char[256];
        for (var c : arr) {
            freArray[c]++;
        }

        char[] sorted = new char[arr.length];
        int cnt = 0;
        for (int i = 0; i < freArray.length; i++) {
            for (int j = 0; j < freArray[i]; j++) {
                sorted[cnt++] = (char) i;
            }
        }
        return sorted;
    }

    /**
     * 2.QUICK SORT
     * ////////////////////////////////////////////////////////////////////////////////////
     * <p>
     * This takes as parameters the array to be sorted, the first and the last
     * index. First, it checks the indices and continues only if there are still
     * elements to be sorted. It gets the index of the sorted pivot and uses it to
     * recursively call the partition() method with the same parameters as the
     * quickSort() method, but with different indices. The last element is selected
     * as a pivot arbitrarily. Then, checks each element and swaps it before the
     * pivot if its value is smaller. By the end of the partitioning, all elements
     * less than the pivot are on the left of it, and all elements greater than the
     * pivot is on the right of it. The pivot is at its final sorted position and
     * the function returns this position.
     * <p>
     * 
     * @param arr to be sorted array
     * @return sorted array
     */
    public static int[] quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    private void quickSort(int[] arr, int beginIndex, int endIndex) {
        if (beginIndex < endIndex) {
            int partIndex = partition(arr, beginIndex, endIndex);

            quickSort(arr, beginIndex, partIndex - 1);
            // next comparing
            quickSort(arr, partIndex + 1, endIndex);
        }
    }

    private int partition(int[] arr, int beginIndex, int endIndex) {
        int i = (beginIndex - 1);

        int pivot = arr[endIndex]; // arbitrary selection
        for (int j = beginIndex; j < endIndex; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, endIndex);
        return i + 1;
    }

    // it switches arr[i] to arr[j]
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 3.MERGE SORT
     * ////////////////////////////////////////////////////////////////////////////////////
     * <p>
     * Merge sort is a sorting algorithm that works by dividing an array into
     * smaller subarrays, sorting each subarray, and then merging the sorted
     * subarrays back together to form the final sorted array. Think of it as a
     * recursive algorithm continuously splits the array in half until it cannot be
     * further divided. This means that if the array becomes empty or has only one
     * element left, the dividing will stop, i.e. it is the base case to stop the
     * recursion. If the array has multiple elements, split the array into halves
     * and recursively invoke the merge sort on each of the halves. Finally, when
     * both halves are sorted, the merge operation is applied. Merge operation is
     * the process of taking two smaller sorted arrays and combining them to
     * eventually make a larger one.
     * <p>
     * 
     * @param arr to be sorted array
     * @return sorted array
     */
    public static int[] mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        sort(arr, 0, arr.length - 1);
        return arr;
    }

    private void sort(int[] arr, int iLeft, int iRight) {
        if (iLeft < iRight) {
            // Find the middle point
            int mIndex = iLeft + (iRight - iLeft) / 2;

            // Sort first subarray
            sort(arr, iLeft, mIndex);
            // Sort second subarray
            sort(arr, mIndex + 1, iRight);
            // Merge the sorted them
            merge(arr, iLeft, mIndex, iRight);
        }
    }

    // Merges two subarrays of arr[]. First subarray is arr[l..m] and Second
    // subarray is arr[m+1..r]
    private void merge(int[] arr, int iLeft, int mIndex, int rIndex) {
        // Find sizes of two subarrays to be merged
        int leftSize = mIndex - iLeft + 1;
        int rightSize = rIndex - mIndex;

        /* Create temp arrays */
        int[] tempLeft = new int[leftSize];
        int[] tempRight = new int[rightSize];

        /* Copy data to temp arrays */
        for (int i = 0; i < leftSize; ++i)
            tempLeft[i] = arr[iLeft + i];
        for (int j = 0; j < rightSize; ++j)
            tempRight[j] = arr[mIndex + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0;
        int j = 0;
        // Initial index of merged subarray array
        int k = iLeft;
        while (i < leftSize && j < rightSize) {
            if (tempLeft[i] <= tempRight[j]) {
                arr[k] = tempLeft[i];
                i++;
            } else {
                arr[k] = tempRight[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < leftSize) {
            arr[k] = tempLeft[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < rightSize) {
            arr[k] = tempRight[j];
            j++;
            k++;
        }
    }

}