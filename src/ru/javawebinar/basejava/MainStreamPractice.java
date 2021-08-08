package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        return Arrays.stream(values).distinct().sorted().reduce(0, (a, b) -> a * 10 + b);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        return integers.stream().filter(n -> sum % 2 == 0 && n % 2 != 0 || sum % 2 != 0 && n % 2 == 0)
                .collect(Collectors.toList());
    }
}
