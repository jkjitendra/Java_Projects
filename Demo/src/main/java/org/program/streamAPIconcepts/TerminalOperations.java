package org.program.streamAPIconcepts;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOperations {

    public static void main(String[] args) {

        // 1. forEach(Consumer<T>):- Performs an action for each element of the stream
        List<Integer> numbersForEach = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbersForEach.stream().forEach(num -> System.out.print(num+ " ")); // Outputs: 1 2 3 4 5 6

        System.out.println();

        // 2. forEachOrdered(Consumer<? super T> action):- Similar to forEach, but respects the encounter order
        // of the stream if the stream has a defined order.
        List<Integer> numbersForEachOrdered = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbersForEachOrdered
                .stream()
                .parallel()
                .forEachOrdered(num -> System.out.print(num)); // Outputs each number in the order of the list,despite parallelism

        System.out.println();

        // 3. toArray():- Returns an array containing the elements of the stream. It collects the elements of the stream into a new array.
        List<Integer> numbersToArray = Arrays.asList(1, 2, 3, 4, 5, 6);
        Object[] elements = numbersToArray.stream().toArray();
        System.out.print(Arrays.toString(elements)); // Outputs "[1, 2, 3, 4, 5, 6]"

        System.out.println();

        // 4. toArray(IntFunction<A[]> generator):- Returns an array containing the elements of the stream, using the provided generator function to allocate the returned array.
        List<Integer> numbersToArrayWithGeneratorFunc = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer[] elementsArray = numbersToArrayWithGeneratorFunc.stream()
                .toArray(Integer[]::new);
        System.out.print(Arrays.toString(elementsArray)); // Outputs "[1, 2, 3, 4, 5, 6]"

        System.out.println();

        // 5. reduce(T identity, BinaryOperator<T> accumulator):- Performs a reduction on the elements of the stream using an initial identity value and an accumulator function. This variant allows for parallel execution because the identity value is both an initial seed value for the reductions and a default result if there are no elements in the stream.
        List<Integer> numbersReduce = Arrays.asList(1, 2, 3, 4, 5, 6);
        int sum = numbersReduce
                    .stream()
                    .reduce(0, (a, b) -> a + b);
        System.out.print(sum); // Outputs "21"

        System.out.println();

        // 6. reduce(BinaryOperator<T> accumulator):- Performs a reduction on the elements of the stream using an associative accumulation function and returns an Optional<T>.
        List<Integer> numbersReduceWithAccumulator = Arrays.asList(11, 31, 13, 24, 5, 26);
        Optional<Integer> max = numbersReduceWithAccumulator.stream()
                .reduce(Integer::max);
        max.ifPresent(System.out::print); // Outputs the maximum element, "31"

        System.out.println();

        // 7. reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner):- Performs a mutable reduction operation on the stream. This is a generalization of reduce() and is intended for parallel processing, combining the accumulator and combiner function.
        List<Integer> numbersReduceWithAccumulatorAnCombiner = Arrays.asList(1, 3, 6, 4, 5, 7);
        int product = numbersReduceWithAccumulatorAnCombiner.stream()
                .reduce(1, (a, b) -> a * b, (a, b) -> a);
        System.out.print(product); // Outputs "2520"

        System.out.println();

        // 8. collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner):- Performs a mutable reduction on the stream using a collector. This is more general than reduce and is intended for efficiently mutable accumulations, such as collecting elements into collections.
        List<Integer> numbersCollect = Arrays.asList(1, 3, 6, 4, 5, 7);
        StringBuilder concatenated = numbersCollect.stream()
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append);
        System.out.print(concatenated.toString()); // Outputs "136457"

        System.out.println();

        // 9. collect(Collector<? super T, A, R> collector):- A more specialized case of collect using the Collector interface, which encapsulates supplier, accumulator, and combiner functions.
        List<Integer> numbersCollectWithGeneralizedFunc = Arrays.asList(1, 3, 6, 4, 5, 7);
        List<Integer> evenNumbers = numbersCollectWithGeneralizedFunc.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.print(evenNumbers); // Outputs "[6, 4]"

        System.out.println();

        // 10. min(Comparator<? super T> comparator):- Returns the minimum element of the stream according to the provided Comparator. This is a special case of reduction.
        List<Integer> numbersMinComparator = Arrays.asList(11, 10, 6, 14, 25, 17);
        Optional<Integer> minNumber = numbersMinComparator.stream().min(Integer::compare);
        minNumber.ifPresent(System.out::print); // Outputs the minimum element, "6"

        System.out.println();

        List<String> numbersMinComparatorList = Arrays.asList("abc", "ab", "c", "abd", "efc", "dfg");
        Optional<String> minElement = numbersMinComparatorList.stream().min(String::compareTo);
        minElement.ifPresent(System.out::print); // Outputs: "ab" lexicographically

        System.out.println();

        Optional<String> minElementByLength = numbersMinComparatorList.stream()
                .min((a, b) -> Integer.compare(a.length(), b.length())); // Compare by length
        minElementByLength.ifPresent(System.out::print); // Outputs: "c" the shortest by length

        System.out.println();

        // 11. max(Comparator<? super T> comparator):- Returns the maximum element of the stream according to the provided Comparator.
        List<Integer> numbersMaxComparator = Arrays.asList(11, 10, 6, 14, 25, 17);
        Optional<Integer> maxNumber = numbersMaxComparator.stream()
                .max(Integer::compare);
        maxNumber.ifPresent(System.out::print); // Outputs the maximum element, "25"

        System.out.println();

        // 12. count():- Returns the count of elements in the stream. This is a special case of reduction resulting in the number of elements in the stream.
        List<Integer> integerList = Arrays.asList(11, 10, 6, 14, 25, 17);
        long count = integerList.stream().count();
        System.out.print(count); // Outputs: total elements "6"

        System.out.println();

        // 13. anyMatch(Predicate<? super T> predicate):- Returns true if any elements of the stream match the provided predicate. Otherwise, it returns false.
        List<Integer> integerListDivisibleBy5 = Arrays.asList(11, 10, 6, 14, 25, 17);
        boolean hasDivisibleBy5 = integerListDivisibleBy5.stream().anyMatch(n -> n % 5 == 0);
        System.out.print(hasDivisibleBy5); // Outputs "true" as 10 is divisible by 5

        System.out.println();

        List<Integer> integerListDivisibleBy8 = Arrays.asList(11, 10, 6, 14, 25, 17);
        boolean hasDivisibleBy8 = integerListDivisibleBy8.stream().anyMatch(n -> n % 8 == 0);
        System.out.print(hasDivisibleBy8); // Outputs "false"

        System.out.println();

        // 14. allMatch(Predicate<? super T> predicate):- Returns true if all elements of the stream match the provided predicate. Otherwise, it returns false.
        List<Integer> integerListAllDivisibleBy2 = Arrays.asList(11, 10, 6, 14, 25, 17);
        boolean allEven = integerListAllDivisibleBy2.stream()
                .allMatch(n -> n % 2 == 0);
        System.out.print(allEven); // Outputs "false"

        System.out.println();

        // 15. noneMatch(Predicate<? super T> predicate):- Returns true if no elements of the stream match the provided predicate. Otherwise, it returns false.
        List<Integer> integerListGreaterThan15 = Arrays.asList(11, 16, 6, 14, 5, 7);
        boolean noneMatch = integerListGreaterThan15.stream()
                .noneMatch(n -> n > 15);
        System.out.print(noneMatch); // Outputs "false"

        System.out.println();

        // 16. findFirst():- Returns an Optional describing the first element of the stream, or an empty Optional if the stream is empty. This is typically used in conjunction with other stream operations.
        List<Integer> integerListFirstElement = Arrays.asList(11, 16, 6, 14, 5, 7);
        Optional<Integer> first = integerListFirstElement.stream().findFirst();
        first.ifPresent(System.out::print); // Outputs "11"

        System.out.println();

        // 17. findAny():- Returns an Optional describing some element of the stream, or an empty Optional if the stream is empty. This is useful in parallel streams where the first found element is returned.
        List<Integer> integerListAnyElement = Arrays.asList(16, 11, 6, 14, 5, 7);
        Optional<Integer> any = integerListAnyElement.stream().findAny();
        any.ifPresent(System.out::print); // Could output any element, often outputs "16"

        System.out.println();

        // 18. summaryStatistics() (for IntStream, LongStream, DoubleStream):- Collects statistics, such as count, min, max, sum, and average, about the elements of the stream.
        List<String> integerListStatistics = Arrays.asList("16", "11", "6", "14", "5", "7");
        IntSummaryStatistics stats = integerListStatistics.stream()
                .mapToInt(Integer::parseInt) // // This is actually redundant in this specific case since the list is already of type Integer
                .summaryStatistics();

        // Printing out the max, min, and average
        System.out.print("Max: " + stats.getMax() +
                ", Min: " + stats.getMin() +
                ", Average: " + stats.getAverage() +
                ", Sum: " + stats.getSum() +
                ", Count: " + stats.getCount());
        // Outputs: Max: 16, Min: 5, Average: 9.833333333333334, Sum: 59, Count: 6

        System.out.println();

    }
}
