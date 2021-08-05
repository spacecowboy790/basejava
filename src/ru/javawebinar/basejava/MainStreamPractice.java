package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MainStreamPractice {

    public static void main(String[] args) {
        int[] values1 = {1, 2, 3, 3, 2, 3};
        int[] values2 = {9, 8};
        System.out.println(minValue(values1));
        System.out.println(minValue(values2));

        System.out.println();

        List<Integer> list1 = new ArrayList<>(Arrays.asList(2, 1, 5, 1, 3, 4)); // sum == 16
        List<Integer> list2 = new ArrayList<>(Arrays.asList(2, 1, 5, 1, 3, 4, 1)); // sum == 17
        System.out.println(oddOrEven(list1));
        System.out.println(oddOrEven(list2));
    }

    public static int minValue(int[] values) {
        int[] arr = Arrays.stream(values).distinct().sorted().toArray();
        return IntStream.range(0, arr.length)
                .map(i -> (int) (Math.pow(10, arr.length - 1 - i) * arr[i]))
                .sum();
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        if (sum % 2 != 0) {
            integers.removeIf(i -> i % 2 != 0);
        }
        else {
            integers.removeIf(i -> i % 2 == 0);
        }
        return integers;
    }
}
