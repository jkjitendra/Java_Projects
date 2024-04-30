package org.program.streamAPIconcepts;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsUsageInStream {

    public static void main(String[] args) {
        //  Basic Collectors
            //  toList(): Collects the elements of the stream into a List.
                // Collecting concatenated strings into a list.
                List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
                List<String> namesList = names.stream()
                        .map(x -> "I am " + x)
                        .collect(Collectors.toList());
                System.out.print(namesList); // Outputs: [I am Alice, I am Bob, I am Charlie, I am David]

                System.out.println();

                // Collecting even numbers into a list.
                List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
                List<Integer> evenNumbers = numbers.stream()
                        .filter(n -> n % 2 == 0)
                        .collect(Collectors.toList());
                System.out.print(evenNumbers); // Outputs: [2, 4, 6]

                System.out.println();

            //  toSet(): Collects the elements of the stream into a Set.
                // Collecting unique words into a set.
                List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "grape");
                Set<String> uniqueWords = words.stream().collect(Collectors.toSet());
                System.out.print(uniqueWords); // Outputs: [banana, orange, apple, grape]

                System.out.println();

                // Collecting distinct letters from a list of words.
                List<String> moreWords = Arrays.asList("hello", "world", "hello", "stream");
                Set<Character> letters = moreWords.stream()
                        .flatMap(word -> word.chars().mapToObj(c -> (char) c))
                        .collect(Collectors.toSet());
                System.out.print(letters); // Outputs: [a, r, s, d, t, e, w, h, l, m, o]

                System.out.println();

            //  toCollection(Supplier<C> collectionFactory): Collects elements into a custom collection created by the provided factory.
                // Collecting numbers into a LinkedList
                List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
                LinkedList<Integer> linkedListOfNumbers = someNumbers.stream().collect(Collectors.toCollection(LinkedList::new));
                System.out.print(linkedListOfNumbers); // Outputs: [1, 2, 3, 4, 5]

                System.out.println();

                // Collecting names into a TreeSet to sort them
                List<String> nameList = Arrays.asList("Alice", "Charlie", "Bob", "David");
                TreeSet<String> sortedNames = nameList.stream().collect(Collectors.toCollection(TreeSet::new));
                System.out.print(sortedNames); // Outputs: [Alice, Bob, Charlie, David]

                System.out.println();

            //  joining(): Concatenates the elements of a CharSequence stream into a single string.
                // Concatenating strings from a list
                List<String> stringList = Arrays.asList("This", "is", "a", "sentence");
                String result = stringList.stream().collect(Collectors.joining(" "));
                System.out.print(result); // Outputs: This is a sentence

                System.out.println();

                // Concatenating numbers with delimiters and a prefix/suffix
                List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5);
                String formattedString = numList.stream()
                                                .map(String::valueOf)
                                                .collect(Collectors.joining(", ", "(", ")"));
                System.out.print(formattedString); // Outputs: (1, 2, 3, 4, 5)

                System.out.println();

        //  Collectors for Summarizing Elements
            //  summingInt(ToIntFunction<? super T> mapper): Produces the sum of a transformed int-valued function applied to the elements.
                // Calculating total age from a list of persons
                class Person {
                    String name;
                    int age;

                    Person(String name, int age) {
                        this.name = name;
                        this.age = age;
                    }
                }

                List<Person> people = Arrays.asList(new Person("Alice", 24), new Person("Bob", 30), new Person("Charlie", 22));
                int totalAge = people.stream().collect(Collectors.summingInt(person -> person.age));
                System.out.print("Total Age: " + totalAge); // Outputs: Total Age: 76

                System.out.println();

                // Summing up lengths of words.
                List<String> wordsForSumming = Arrays.asList("hello", "world", "java", "stream");
                int totalLength = wordsForSumming.stream().collect(Collectors.summingInt(String::length));
                System.out.print("Total Length of Words: " + totalLength); // Outputs: Total Length of Words: 20

                System.out.println();

            //  summingLong(ToLongFunction<? super T> mapper): Similar to summingInt, but for long values
                // Summing long values representing file sizes.
                List<Long> fileSizes = Arrays.asList(1024L, 2048L, 512L);
                long totalSize = fileSizes.stream().collect(Collectors.summingLong(Long::longValue));
                System.out.print("Total File Size: " + totalSize + " bytes"); // Outputs: Total File Size: 3584 bytes

                System.out.println();

                // Calculating total milliseconds from a list of events.
                class Event {
                    long duration;

                    Event(long duration) {
                        this.duration = duration;
                    }
                }

                List<Event> events = Arrays.asList(new Event(100), new Event(500), new Event(300));
                long totalDuration = events.stream().collect(Collectors.summingLong(event -> event.duration));
                System.out.print("Total Event Duration: " + totalDuration + " ms"); // Outputs: Total Event Duration: 900 ms

                System.out.println();

            //  summingDouble(ToDoubleFunction<? super T> mapper): Similar to summingInt, but for double values.
                // Summing up scores from a list of doubles.
                List<Double> scores = Arrays.asList(95.5, 82.3, 90.4);
                double totalScores = scores.stream().collect(Collectors.summingDouble(Double::doubleValue));
                System.out.print("Total Scores: " + totalScores); // Outputs: Total Scores: 268.2

                System.out.println();

                // Calculating total weight from a list of products.
                class Product {
                    double weight;

                    Product(double weight) {
                        this.weight = weight;
                    }
                }

                List<Product> products = Arrays.asList(new Product(4.5), new Product(3.2), new Product(1.8));
                double totalWeight = products.stream().collect(Collectors.summingDouble(product -> product.weight));
                System.out.print("Total Weight: " + totalWeight + " kg"); // Outputs: Total Weight: 9.5 kg

                System.out.println();
        
        //  Collectors for Averaging
            //  averagingInt(ToIntFunction<? super T> mapper): Computes the average of an int-valued function over the elements.
                // Average age calculation
                double averageAge = people.stream().collect(Collectors.averagingInt(person -> person.age));
                System.out.print("Average Age: " + averageAge); // Outputs: Average Age: 25.333333333333332

                System.out.println();

                // Average length of strings.
                double averageLength = wordsForSumming.stream().collect(Collectors.averagingInt(String::length));
                System.out.print("Average Length of Words: " + averageLength); // Outputs: Average Length of Words: 5.0

                System.out.println();

            //  averagingLong(ToLongFunction<? super T> mapper): Computes the average for long values.
                // Average file size calculation.
                double averageFileSize = fileSizes.stream().collect(Collectors.averagingLong(Long::longValue));
                System.out.print("Average File Size: " + averageFileSize + " bytes"); // Outputs: Average File Size: 1194.6666666666667 bytes

                System.out.println();

                // Average duration of events.
                double averageEventDuration = events.stream().collect(Collectors.averagingLong(event -> event.duration));
                System.out.print("Average Event Duration: " + averageEventDuration + " ms"); // Outputs: Average Event Duration: 300.0 ms

                System.out.println();

            //  averagingDouble(ToDoubleFunction<? super T> mapper): Computes the average for double values.
                // Average score calculation
                double averageScore = scores.stream().collect(Collectors.averagingDouble(Double::doubleValue));
                System.out.print("Average Score: " + averageScore); // Outputs: Average Score: 89.39999999999999

                System.out.println();

                // Average product weight calculation
                double averageProductWeight = products.stream().collect(Collectors.averagingDouble(product -> product.weight));
                System.out.print("Average Product Weight: " + averageProductWeight + " kg"); // Outputs: Average Product Weight: 3.1666666666666665 kg

                System.out.println();

        //  Specialized Reductions
            //  reducing(BinaryOperator<T> op): Performs a reduction on the elements, using an associative accumulation function.
                // Reduce to find the longest string
                List<String> wordsForReducing = Arrays.asList("hello", "world", "java", "stream");
                Optional<String> longestWord = wordsForReducing.stream().collect(Collectors.reducing((s1, s2) -> s1.length() > s2.length() ? s1 : s2));
                longestWord.ifPresent(System.out::print); // Outputs the longest word: stream

                System.out.println();

                // Reduce to find the maximum integer.
                List<Integer> numbersForReducing = Arrays.asList(11, 26, 53, 14, 53, 36);
                Optional<Integer> maxNumber = numbersForReducing.stream().collect(Collectors.reducing(Integer::max));
                maxNumber.ifPresent(System.out::print); // Outputs the maximum number: 53

                System.out.println();

            //  reducing(T identity, BinaryOperator<T> op): A more generalized form of reducing that includes an identity value.
                // Reducing with identity to calculate total age with a starting age
                List<Person> peopleData = Arrays.asList(new Person("Alice", 24), new Person("Bob", 30), new Person("Charlie", 22));
                int totalAgeWithStart = peopleData.stream().collect(Collectors.reducing(100, person -> person.age, Integer::sum));
                System.out.print("Total Age starting from 100: " + totalAgeWithStart); // Outputs: Total Age starting from 100: 176

                System.out.println();

                // Reducing to concatenate strings with a prefix.
                String allNames = people.stream()
                                        .map(person -> person.name)
                                        .collect(Collectors.reducing("Names: ", (s1, s2) -> s1 + " " + s2));
                System.out.print(allNames); // Outputs: Names: Alice Bob Charlie

                System.out.println();

            //  reducing(U identity, Function<? super T, U> mapper, BinaryOperator<U> op): Maps values to another type before reducing.
                // Reducing to calculate the total length of names starting from a base value
                List<Person> peoplesData = Arrays.asList(new Person("Ali", 24), new Person("Bob", 30), new Person("Charlie", 22));
                int totalLengthOfNames = peoplesData.stream().collect(Collectors.reducing(0, person -> person.name.length(), Integer::sum));
                System.out.print("Total Length of Names starting from 0: " + totalLengthOfNames); // Outputs: Total Length of Names starting from 0: 13

                System.out.println();
    }
}
