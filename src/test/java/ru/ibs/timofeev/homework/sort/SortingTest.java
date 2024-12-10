package ru.ibs.timofeev.homework.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static ru.ibs.timofeev.homework.sort.BubbleSort.bubbleSort;
import static ru.ibs.timofeev.homework.sort.MergeSort.mergeSort;
import static ru.ibs.timofeev.homework.sort.QuickSort.quickSort;
import static ru.ibs.timofeev.homework.sort.TimSort.timSort;

public class SortingTest {

    private int[] arr;
    private int[] expectedArr;
    private final int SIZE = 10_000;
    private final int BOUND = 1_000;

    @BeforeEach
    public void setUp() {
        Random random = new Random();

        arr = new int[SIZE];
        for (int i = 0; i < arr.length; i++)
            arr[i] = random.nextInt(2 * BOUND) - BOUND;

        expectedArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expectedArr);
    }

    @Test
    public void arrayShouldBeSortedInAscOrderAfterTimSort() {
        timSort(arr, arr.length);
        Assertions.assertArrayEquals(arr, expectedArr);
    }

    @Test
    public void arrayShouldBeSortedInAscOrderAfterMergeSort() {
        mergeSort(arr, 0, arr.length - 1);
        Assertions.assertArrayEquals(arr, expectedArr);
    }

    @Test
    public void arrayShouldBeSortedInAscOrderAfterBubbleSort() {
        bubbleSort(arr, arr.length);
        Assertions.assertArrayEquals(arr, expectedArr);
    }

    @Test
    public void arrayShouldBeSortedInAscOrderAfterQuickSort() {
        quickSort(arr, 0, arr.length - 1);
        Assertions.assertArrayEquals(arr, expectedArr);
    }
}
