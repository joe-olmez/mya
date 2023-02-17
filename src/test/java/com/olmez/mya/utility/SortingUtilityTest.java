package com.olmez.mya.utility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SortingUtilityTest {

    private int[] arr = { 10, 9, 1, 5, 7, 2, 6, 8, 4, 3 };
    private int[] sortedArr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    private int[] emptyArray = {};

    private char[] cArr = { 'z', 'k', 'b', 't', 'd', 'c', 'a', 'n', 'o', 'm', 'c', 'e', 'e', 'g', 'f', 'e', 'a' };
    private char[] sortedC = { 'a', 'a', 'b', 'c', 'c', 'd', 'e', 'e', 'e', 'f', 'g', 'k', 'm', 'n', 'o', 't', 'z' };

    @Test
    void testCountSort() {
        // int array
        int[] iRes = SortingUtility.countSort(arr);
        assertThat(iRes).isEqualTo(sortedArr);

        // char array
        char[] cRes = SortingUtility.countSort(cArr);
        assertThat(cRes).isEqualTo(sortedC);

        // for null value
        arr = null;
        assertThat(SortingUtility.countSort(arr)).isNull();

        // for empty array
        assertThat(SortingUtility.countSort(emptyArray)).isEmpty();
    }

    @Test
    void testQuickSort() {
        // for null value
        assertThat(SortingUtility.quickSort(null)).isNull();

        // for empty array
        assertThat(SortingUtility.quickSort(emptyArray)).isEmpty();

        var result = SortingUtility.quickSort(arr);
        assertThat(result).isEqualTo(sortedArr);
    }

    @Test
    void testMergeSort() {
        // for null value
        assertThat(SortingUtility.mergeSort(null)).isNull();

        // for empty array
        assertThat(SortingUtility.mergeSort(emptyArray)).isEmpty();

        var result = SortingUtility.mergeSort(arr);
        assertThat(result).isEqualTo(sortedArr);
    }

}
