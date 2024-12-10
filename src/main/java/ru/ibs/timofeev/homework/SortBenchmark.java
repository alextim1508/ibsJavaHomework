package ru.ibs.timofeev.homework;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static ru.ibs.timofeev.homework.sort.BubbleSort.bubbleSort;
import static ru.ibs.timofeev.homework.sort.MergeSort.mergeSort;
import static ru.ibs.timofeev.homework.sort.QuickSort.quickSort;
import static ru.ibs.timofeev.homework.sort.TimSort.timSort;

@State(Scope.Thread)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 1, time = 1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SortBenchmark {

    private int[] arr;
    private final int BOUND = 1_000;

    @Param({"100", "1000", "10000"})
    private int size;

    @Param({"asc", "desc", "rand"})
    public String order;

    @Setup(Level.Invocation)
    public void init() {
        arr = new int[size];

        if ("asc".equals(order)) {
            for (int i = 0; i < arr.length; i++)
                arr[i] = i;

        } else if ("desc".equals(order)) {
            for (int i = 0; i < arr.length; i++)
                arr[i] = arr.length - 1 - i;

        } else if ("rand".equals(order)) {
            Random random = new Random();
            for (int i = 0; i < arr.length; i++)
                arr[i] = random.nextInt(2 * BOUND) - BOUND;
        }
    }

    @Benchmark
    public void timSortBenchmark(Blackhole blackhole) {
        timSort(arr, arr.length);
        blackhole.consume(arr);
    }

    @Benchmark
    public void mergeSortBenchmark(Blackhole blackhole) {
        mergeSort(arr, 0, arr.length - 1);
        blackhole.consume(arr);
    }

    @Benchmark
    public void bubbleSortBenchmark(Blackhole blackhole) {
        bubbleSort(arr, arr.length);
        blackhole.consume(arr);
    }

    @Benchmark
    public void quickSortBenchmark(Blackhole blackhole) {
        quickSort(arr, 0, arr.length - 1);
        blackhole.consume(arr);
    }
}
