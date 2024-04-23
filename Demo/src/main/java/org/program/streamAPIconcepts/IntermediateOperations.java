package org.program.streamAPIconcepts;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

public class IntermediateOperations {

    // Intermediate operations are those that transform a stream into another stream.
    // These operations are lazy, meaning they don't actually perform any processing until a terminal operation is executed on the stream.

    public static void main(String[] args) {

        // 1. filter(Predicate<T>):- Filters elements based on a condition. Only elements that satisfy the condition are included in the resultant stream.
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Stream<Integer> evenNumbers = numbers.stream()
                                             .filter(n -> n % 2 == 0);
        evenNumbers.forEach(System.out::print); // Outputs 246
        System.out.println();
        // or if we don't want to store the list of evenNumbers then directly print them
        List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbersList.stream()
                    .filter(n -> n % 2 == 0)
                    .forEach(System.out::print); // Outputs 246

        System.out.println();

        // 2. map(Function<T, R>):- Transforms the elements of the stream by applying a function to each element, turning each element into another form (possibly a new type).
        List<String> words = Arrays.asList("hello ", "world ", "stream ", "api ");
        Stream<Integer> eachWordslength = words.stream()
                                               .map(String::length);
        eachWordslength.forEach(System.out::print); // Outputs 5563

        System.out.println();

        // 3. flatMap(Function<T, Stream<R>>):- Flattens multiple streams into a single stream. Useful when each element of a stream is a stream itself, or can be transformed into a stream.
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("a ", "b "),
                Arrays.asList("c ", "d ", "e ")
        );
        Stream<String> flatStream = listOfLists.stream()
                                               .flatMap(List::stream);
        flatStream.forEach(System.out::print); // Outputs a b c d e

        System.out.println();

        // 4. sorted():- Sorts the elements of the stream. It can use natural ordering or a provided Comparator.
        Stream<String> sortedWords = words.stream().sorted();
        sortedWords.forEach(System.out::print); // Outputs api hello stream world

        System.out.println();

        // 5. distinct():- Removes duplicate elements from the stream, ensuring that only unique elements are passed through.
        List<Integer> numbersWithDuplicateItems = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        Stream<Integer> distinctNumbers = numbersWithDuplicateItems.stream().distinct();
        distinctNumbers.forEach(System.out::print); // Outputs 12345

        System.out.println();

        List<String> stringWithDuplicate= Arrays.asList("hello ", "world ", "Hi ", "HELLO ", "Hi ", "Bye ");
        Stream<String> distinctNumbersList = stringWithDuplicate.stream().distinct();
        distinctNumbersList.forEach(System.out::print); // Outputs hello world Hi HELLO Bye

        System.out.println();

        // 6. limit(long maxSize):- Limits the number of elements in a stream to the specified maximum size.
        List<String> listOfString= Arrays.asList("hello ", "World ", "Hi ", "HELLO ", "Hi ", "Bye ");
        Stream<String> limitedNumbers = listOfString.stream().limit(2);
        limitedNumbers.forEach(System.out::print); // Outputs hello World

        System.out.println();

        // 7. peek(Consumer<T>):- Performs the specified action on each element of the stream as elements are consumed from the resulting stream.
        // Commonly used for debugging purposes to see the elements as they flow past a certain point in a stream pipeline.
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> doubledIntegerList = integerList.stream()
                .peek(n -> System.out.print("Original: " + n)) // Original: 1 Original: 2 Original: 3 Original: 4 Original: 5
                .map(n -> n * 2)
                .peek(n -> System.out.print("Doubled: " + n)) //   Doubled: 2 Doubled: 4 Doubled: 6 Doubled: 8 Doubled: 10
                .collect(Collectors.toList());

        System.out.println();

        // 8. skip(long n):- Skips the first n elements of the stream.
        List<Integer> listOfIntegers = Arrays.asList(1, 2, 3, 4, 5, 8, 20);
        Stream<Integer> skippedNumbers = listOfIntegers.stream().skip(3); // Skips the first two elements
        skippedNumbers.forEach(System.out::print); // Outputs 45820

        System.out.println();

        // 9. concat(Stream<? extends T> a, Stream<? extends T> b):- Merges two streams into one.
        Stream<String> streamOfFruits = Stream.of("Apple ", "Banana ");
        Stream<String> streamOfDryFruits = Stream.of("Almonds ", "Pistachio ");
        Stream<String> concatenated = Stream.concat(streamOfFruits, streamOfDryFruits);
        concatenated.forEach(System.out::print);  // Outputs: Apple Banana Almonds Pistachio

        System.out.println();

        Stream<Stream<String>> streamOfVegetables = Stream.of(Stream.of("Gobhi ", "Lady_Finger "), Stream.of("Lauki ", "Karela "));
        Stream<Stream<String>> streamOfSalads = Stream.of(Stream.of("Cucumber ", "Carrot "), Stream.of("Radish ", "Cabbage "));
        Stream<Stream<String>> concatenatedStreams = Stream.concat(streamOfVegetables, streamOfSalads);
        concatenatedStreams.flatMap(s -> s).forEach(System.out::print);  // Outputs: Gobhi Lady_Finger Lauki Karela Cucumber Carrot Radish Cabbage

        System.out.println();

        // 10. flatMapToInt(Function<? super T, ? extends IntStream> mapper):- Converts complex structures into a flat IntStream
        List<List<Integer>> listOfLisOfIntegers = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4, 5));
        IntStream intFlatStream = listOfLisOfIntegers.stream()
                                                     .flatMapToInt(subList -> subList.stream().mapToInt(Integer::intValue));
        intFlatStream.forEach(System.out::print);  // Outputs: 12345

        System.out.println();

        // 11. flatMapToLong(Function<? super T, ? extends LongStream> mapper):- Flattens a stream of collections into a LongStream.
        List<List<Long>> listOfListOfLong = Arrays.asList(Arrays.asList(2L, 8L, 4L), Arrays.asList(19L, 3L, 7L));
        LongStream longFlatStream = listOfListOfLong.stream()
                                                    .flatMapToLong(subList -> subList.stream().mapToLong(Long::longValue));
        longFlatStream.forEach(System.out::print);  // Outputs: 2841937

        System.out.println();

        // 12. flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper):- Converts collections into a DoubleStream for floating-point operations.
        List<List<Double>> listOfListOfDouble = Arrays.asList(Arrays.asList(2.7, 3.9, 4.6), Arrays.asList(1.3, 5.3, 7.0));
        // Using Stream flatMapToDouble(Function mapper)
        DoubleStream doubleStream = listOfListOfDouble.stream()
                                                      .flatMapToDouble(subList -> subList.stream().mapToDouble(Double::doubleValue));
        doubleStream.forEach(System.out::println); // Outputs: 2.7 3.9 4.6 1.3 5.3 7.0

        System.out.println();

        // 13. mapToInt(ToIntFunction<? super T> mapper):- Maps objects to an IntStream
        Stream<String> stringStreamForInt = Stream.of("hello", "world", "Bye");
        IntStream lengthsOfEachStringInInteger = stringStreamForInt.mapToInt(String::length);
        lengthsOfEachStringInInteger.forEach(System.out::print);  // Outputs: 553

        System.out.println();

        // 14. mapToLong(ToLongFunction<? super T> mapper):- Maps objects to a LongStream
        Stream<String> stringStreamForLong = Stream.of("a", "ccc", "bb");
        LongStream lengthsOfEachStringInLong = stringStreamForLong.mapToLong(String::length);
        lengthsOfEachStringInLong.forEach(System.out::print);  // Outputs: 132

        System.out.println();

        // 15. mapToDouble(ToDoubleFunction<? super T> mapper):- Maps objects to a DoubleStream.
        Stream<String> stringStreamForDouble = Stream.of("a", "ccc", "bb");
        DoubleStream lengthsOfEachStringInDouble = stringStreamForDouble.mapToDouble(String::length);
        lengthsOfEachStringInDouble.forEach(System.out::println);  // Outputs: 1.0 3.0 2.0

        System.out.println();

        // 16. onClose(Runnable closeHandler):- Adds a close handler to the stream.
        Stream<String> stream = Stream.of("cleanup", "stream").onClose(() -> System.out.println("\nStream closed"));
        stream.forEach(System.out::print);
        stream.close();  // Outputs: "cleanup stream Stream closed"

        System.out.println();

        // 17. unordered():- Hints that the order of elements does not matter.
        Stream<String> stringStream = Stream.of("hello", "world", "I", "am", "here").unordered();
        stringStream.forEach(System.out::print);  // Outputs:- helloworldIamhere   // Note:- Order may vary, typically it's the same because of the source


    }
}
