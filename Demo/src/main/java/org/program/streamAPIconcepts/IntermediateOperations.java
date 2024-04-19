package org.program.streamAPIconcepts;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<String> words = Arrays.asList("hello", "world", "stream", "api");
        Stream<Integer> eachWordslength = words.stream()
                                               .map(String::length);
        eachWordslength.forEach(System.out::print); // Outputs 5563

        System.out.println();

        // 3. flatMap(Function<T, Stream<R>>):- Flattens multiple streams into a single stream. Useful when each element of a stream is a stream itself, or can be transformed into a stream.
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d", "e")
        );
        Stream<String> flatStream = listOfLists.stream()
                                               .flatMap(List::stream);
        flatStream.forEach(System.out::print); // Outputs abcde

        System.out.println();

        // 4. sorted():- Sorts the elements of the stream. It can use natural ordering or a provided Comparator.
        Stream<String> sortedWords = words.stream().sorted();
        sortedWords.forEach(System.out::print); // Outputs apihellostreamworld

        System.out.println();

        // 5. distinct():- Removes duplicate elements from the stream, ensuring that only unique elements are passed through.
        List<Integer> numbersWithDuplicateItems = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        Stream<Integer> distinctNumbers = numbersWithDuplicateItems.stream().distinct();
        distinctNumbers.forEach(System.out::print); // Outputs 12345

        System.out.println();

        List<String> stringWithDuplicate= Arrays.asList("hello", "world", "Hi", "HELLO", "Hi", "Bye");
        Stream<String> distinctNumbersList = stringWithDuplicate.stream().distinct();
        distinctNumbersList.forEach(System.out::print); // Outputs helloworldHiHELLOBye

        System.out.println();

        // 6. limit(long maxSize):- Limits the number of elements in a stream to the specified maximum size.
        List<String> listOfString= Arrays.asList("hello", "World", "Hi", "HELLO", "Hi", "Bye");
        Stream<String> limitedNumbers = listOfString.stream().limit(2);
        limitedNumbers.forEach(System.out::print); // Outputs helloWorld

        System.out.println();

        // 7. peek(Consumer<T>):- Performs the specified action on each element of the stream as elements are consumed from the resulting stream.
        // Commonly used for debugging purposes to see the elements as they flow past a certain point in a stream pipeline.
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> doubledIntegerList = integerList.stream()
                .peek(n -> System.out.print("Original: " + n))
                .map(n -> n * 2)
                .peek(n -> System.out.print("Doubled: " + n))
                .collect(Collectors.toList());

        System.out.println();

        // 8. skip(long n):- Skips the first n elements of the stream.
        List<Integer> listOfIntegers = Arrays.asList(1, 2, 3, 4, 5, 8, 20);
        Stream<Integer> skippedNumbers = listOfIntegers.stream().skip(3); // Skips the first two elements
        skippedNumbers.forEach(System.out::print); // Outputs 345

        System.out.println();


    }
}
