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

        //  Collectors for Summing Elements
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
        
                // Reducing to sum product weights starting from a base weight.
                double totalProductWeight = products.stream()
                                                    .collect(Collectors.reducing(10.0, product -> product.weight, Double::sum));
                System.out.print("Total Weight starting from 10 kg: " + totalProductWeight + " kg"); // Outputs: Total Weight starting from 10 kg: 19.5 kg

                System.out.println();

        //  Grouping Elements
            //  groupingBy(Function<? super T, ? extends K> classifier): Groups elements by a classifier function.
                // Grouping persons by age.
                List<Person> peopleGroupData = Arrays.asList(
                        new Person("Alice", 24), new Person("Bob", 30), new Person("Charlie", 22),
                        new Person("Drane", 22), new Person("Evans", 24), new Person("Fanthon", 30),
                        new Person("Gotham", 22), new Person("Helana", 24), new Person("Inthoque", 30),
                        new Person("John", 30), new Person("Klian", 22), new Person("Leonard", 24),
                        new Person("Megasthali", 22), new Person("Neontha", 30), new Person("Oreman", 24)
                );
                Map<Integer, List<Person>> groupedByAge = peopleGroupData.stream().collect(Collectors.groupingBy(person -> person.age));
                groupedByAge.forEach((age, persons) -> {
                    System.out.print("Age: " + age + " ");
                    persons.forEach(person -> System.out.print(person.name + ", "));
                    System.out.println();
                }); // Outputs: Age: 22 Charlie, Drane, Gotham, Klian, Megasthali,
                    //          Age: 24 Alice, Evans, Helana, Leonard, Oreman,
                    //          Age: 30 Bob, Fanthon, Inthoque, John, Neontha,

                System.out.println();

                // Grouping words by their length.
                List<String> wordsForGrouping = Arrays.asList("hello", "world", "java", "stream", "spring", "group", "thread");
                Map<Integer, List<String>> groupedByLength = wordsForGrouping.stream().collect(Collectors.groupingBy(String::length));
                groupedByLength.forEach((len, ws) -> System.out.println("Length: " + len + ", Words: " + ws)); // Outputs: Length: 4, Words: [java]
                                                                                                               //          Length: 5, Words: [hello, world, group]
                                                                                                               //          Length: 6, Words: [stream, spring, thread]

                System.out.println();

            //  groupingBy(Function<? super T, ? extends K> classifier, Collector<? super T, A, D> downstream): Groups elements, applying a downstream collector to the results.
                // Grouping persons by city and counting them
                class Persons {
                    String name;
                    int age;
                    String city;

                    Persons(String name, int age, String city) {
                        this.name = name;
                        this.age = age;
                        this.city = city;
                    }
                }
                List<Persons> peoplesGroupData = Arrays.asList(
                        new Persons("Alice", 24, "Pune"), new Persons("Bob", 30, "Indore"), new Persons("Charlie", 22, "Bangalore"),
                        new Persons("Drane", 22, "Indore"), new Persons("Evans", 24, "Pune"), new Persons("Fanthon", 30, "Indore"),
                        new Persons("Gotham", 22, "Indore"), new Persons("Helana", 24, "Bangalore"), new Persons("Inthoque", 30, "Pune")
                );
                Map<String, Long> countByCity = peoplesGroupData.stream()
                                                                .collect(Collectors.groupingBy(person -> person.city, Collectors.counting()));
                countByCity.forEach((city, count) -> System.out.println("City: " + city + ", Count: " + count)); // Outputs: City: Pune, Count: 3
                                                                                                                 //          City: Indore, Count: 4
                                                                                                                 //          City: Bangalore, Count: 2

                System.out.println();

                // Grouping numbers by parity and listing them.
                List<Integer> numbersForGrouping = Arrays.asList(11, 26, 53, 14, 53, 36);
                Map<String, List<Integer>> numbersByParity = numbersForGrouping.stream()
                                                                               .collect(Collectors.groupingBy(n -> n % 2 == 0 ? "Even" : "Odd", Collectors.toList()));
                numbersByParity.forEach((type, nums) -> System.out.println("Type: " + type + ", Numbers: " + nums)); // Outputs: Type: Even, Numbers: [26, 14, 36]
                                                                                                                     //          Type: Odd,  Numbers: [11, 53, 53]

                System.out.println();

            //  groupingByConcurrent(Function<? super T, ? extends K> classifier): A concurrent version of groupingBy.
                // Concurrent grouping of numbers by parity.
                List<Integer> numbersForGroupingConcurrent = Arrays.asList(11, 26, 53, 14, 53, 36);
                ConcurrentMap<String, List<Integer>> concurrentNumbersByParity = numbersForGroupingConcurrent.parallelStream()
                                                                                                             .collect(Collectors.groupingByConcurrent(n -> n % 2 == 0 ? "Even" : "Odd"));
                concurrentNumbersByParity.forEach((type, nums) -> System.out.println("Type: " + type + ", Numbers: " + nums)); // Outputs: Type: Even, Numbers: [26, 14, 36]
                                                                                                                               //          Type: Odd,  Numbers: [11, 53, 53]

                System.out.println();

                // Concurrent grouping of words by their first letter.
                List<String> wordsForConcurrentGrouping = Arrays.asList("apple", "banana", "apple", "orange", "banana", "grape");
                ConcurrentMap<Character, List<String>> wordsByFirstLetter = wordsForConcurrentGrouping.parallelStream()
                                                                                                      .collect(Collectors.groupingByConcurrent(w -> w.charAt(0)));
                wordsByFirstLetter.forEach((letter, ws) -> System.out.println("Letter: " + letter + ", Words: " + ws)); // Outputs: Letter: a, Words: [apple, apple]
                                                                                                                        //          Letter: b, Words: [banana, banana]
                                                                                                                        //          Letter: g, Words: [grape]
                                                                                                                        //          Letter: o, Words: [orange]
        
                System.out.println();
        
        //  Partitioning Elements
            //  partitioningBy(Predicate<? super T> predicate): Partitions elements according to a predicate.
                // Partitioning numbers into even and odd.
                List<Integer> numbersForPartitioning = Arrays.asList(11, 26, 53, 14, 53, 36);
                Map<Boolean, List<Integer>> partitionedNumbers = numbersForPartitioning.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
                partitionedNumbers.forEach((isEven, nums) -> System.out.println("Is Even: " + isEven + ", Numbers: " + nums)); // Outputs: Is Even: false, Numbers: [11, 53, 53]
                                                                                                                               //          Is Even: true,  Numbers: [26, 14, 36]

                System.out.println();

                // Partitioning people into adults and minors.
                List<Persons> peoplesPartitioningData = Arrays.asList(
                        new Persons("Alice", 12, "Pune"), new Persons("Bob", 30, "Indore"), new Persons("Charlie", 22, "Bangalore"),
                        new Persons("Drane", 15, "Indore"), new Persons("Evans", 24, "Pune"), new Persons("Fanthon", 18, "Indore"),
                        new Persons("Gotham", 14, "Indore"), new Persons("Helana", 24, "Bangalore"), new Persons("Inthoque", 17, "Pune")
                );
                Map<Boolean, List<Persons>> partitionedPeople = peoplesPartitioningData.stream().collect(Collectors.partitioningBy(p -> p.age >= 18));
                partitionedPeople.forEach((isAdult, ps) -> {
                    System.out.print("Is Adult: " + isAdult + " [ ");
                    ps.forEach(p -> System.out.print(p.name + ", "));
                    System.out.println("]");
                }); // Outputs: Is Adult: false [ Alice, Drane, Gotham, Inthoque, ]
                    //          Is Adult: true  [ Bob, Charlie, Evans, Fanthon, Helana, ]

                System.out.println();

            //  partitioningBy(Predicate<? super T> predicate, Collector<? super T, A, D> downstream): Partitions elements and applies a downstream collector to the results.
                // Partitioning words by length and counting each group.
                List<String> wordsForPartitioning = Arrays.asList("hello", "world", "java", "stream", "spring", "group", "thread");
                Map<Boolean, Long> wordsPartitionedByLength = wordsForPartitioning.stream()
                                                                                  .collect(Collectors.partitioningBy(w -> w.length() > 5, Collectors.counting()));
                wordsPartitionedByLength.forEach((isLonger, count) -> System.out.println("Is Longer Than 5: " + isLonger + ", Count: " + count)); // Outputs: Is Longer Than 5: false, Count: 4
                                                                                                                                                  //          Is Longer Than 5: true,  Count: 3

                System.out.println();

                // Partitioning numbers into primes and non-primes and collecting them into lists
                List<Integer> numbersPartitioning = Arrays.asList(11, 26, 53, 14, 13, 36);
                Map<Boolean, List<Integer>> primePartition = numbersPartitioning.stream()
                        .collect(Collectors.partitioningBy(CollectorsUsageInStream::isPrime, Collectors.toList()));
                primePartition.forEach((isPrime, nums) -> System.out.println("Is Prime: " + isPrime + ", Numbers: " + nums)); // Outputs: Is Prime: false, Numbers: [26, 14, 36]
                                                                                                                              //          Is Prime: true,  Numbers: [11, 53, 13]

                System.out.println();

        //  Advanced Collectors
            //  collectingAndThen(Collector<T, A, R> downstream, Function<R, RR> finisher): Adapts a collector to perform an additional finishing transformation.
                // Collecting names into a list and then converting it to an immutable list.
                List<Persons> peoplesImmutable = Arrays.asList(
                        new Persons("Alice", 12, "Pune"), new Persons("Bob", 30, "Indore"), new Persons("Charlie", 22, "Bangalore"),
                        new Persons("Drane", 15, "Indore"), new Persons("Evans", 24, "Pune"), new Persons("Fanthon", 18, "Indore"),
                        new Persons("Gotham", 14, "Indore"), new Persons("Helana", 24, "Bangalore"), new Persons("Inthoque", 17, "Pune")
                );
                List<String> immutableNames = peoplesImmutable.stream()
                                                    .map(person -> person.name)
                                                    .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
                System.out.print("Immutable Names: " + immutableNames); // Outputs: Immutable Names: [Alice, Bob, Charlie, Drane, Evans, Fanthon, Gotham, Helana, Inthoque]

                System.out.println();

                // Collecting elements into a set and then converting it to a sorted list.
                List<Integer> numbersSorting = Arrays.asList(11, 26, 53, 14, 13, 36);
                List<Integer> sortedSet = numbersSorting.stream().collect(Collectors.collectingAndThen(Collectors.toSet(), set -> new ArrayList<>(new TreeSet<>(set))));
                System.out.print("Sorted Set: " + sortedSet); // Outputs: Sorted Set: [11, 13, 14, 26, 36, 53]

                System.out.println();

            //  mapping(Function<? super T, ? extends U> mapper, Collector<? super U, A, R> downstream): Adapts a collector to another type by applying a mapping function before collecting.
                // Mapping person names and collecting them into a list.
                List<Persons> peoplesList = Arrays.asList(
                        new Persons("Alice", 12, "Pune"), new Persons("Bob", 30, "Indore"), new Persons("Charlie", 22, "Bangalore"),
                        new Persons("Drane", 15, "Indore"), new Persons("Evans", 24, "Pune"), new Persons("Fanthon", 18, "Indore"),
                        new Persons("Gotham", 14, "Indore"), new Persons("Helana", 24, "Bangalore"), new Persons("Inthoque", 17, "Pune")
                );
                List<String> personNames = peoplesList.stream().collect(Collectors.mapping(person -> person.name, Collectors.toList()));
                System.out.print("Person Names as a List: " + personNames); // Outputs: Person Names as a List: [Alice, Bob, Charlie, Drane, Evans, Fanthon, Gotham, Helana, Inthoque]

                System.out.println();

                // Mapping product weights and collecting them into a set to eliminate duplicates.
                List<Product> productsCollection = Arrays.asList(
                        new Product(4.5), new Product(3.2), new Product(1.8),
                        new Product(4.3), new Product(3.9), new Product(1.5),
                        new Product(4.5), new Product(3.2), new Product(1.8),
                        new Product(4.3), new Product(3.9), new Product(1.5)
                );
                Set<Double> productWeights = productsCollection.stream().collect(Collectors.mapping(product -> product.weight, Collectors.toSet()));
                System.out.print("Product Weights: " + productWeights); // Outputs: Product Weights: [1.8, 4.5, 4.3, 3.2, 1.5, 3.9]

                System.out.println();

            //  flatMapping(Function<? super T, ? extends Stream<? extends U>> mapper, Collector<? super U, A, R> downstream): Similar to mapping, but flattens a stream before collecting.
                // Flattening person hobbies and collecting into a set.
                class PersonData {
                    String name;
                    int age;
                    String city;
                    List<String> hobbies;

                    PersonData(String name, int age, String city, List<String> hobbies) {
                        this.age = age;
                        this.name = name;
                        this.city = city;
                        this.hobbies = hobbies;
                    }
                }
                List<PersonData> peoplesCollectionList = Arrays.asList(
                        new PersonData("Alice", 12, "Pune", Arrays.asList("badminton", "volleyball", "basketball", "football", "chess", "carrom")), new PersonData("Bob", 30, "Indore", Arrays.asList("badminton", "volleyball", "carrom")), new PersonData("Charlie", 22, "Bangalore", Arrays.asList("football", "chess", "carrom")),
                        new PersonData("Drane", 15, "Indore", Arrays.asList("badminton", "basketball", "football", "carrom")), new PersonData("Evans", 24, "Pune", Arrays.asList("badminton", "volleyball", "chess", "carrom")), new PersonData("Fanthon", 18, "Indore", Arrays.asList("basketball", "football", "chess", "carrom")),
                        new PersonData("Gotham", 14, "Indore", Arrays.asList("badminton", "volleyball", "football", "chess")), new PersonData("Helana", 24, "Bangalore", Arrays.asList("badminton", "volleyball", "basketball", "football")), new PersonData("Inthoque", 17, "Pune", Arrays.asList("volleyball","football", "chess", "carrom"))
                );
                Set<String> allHobbies = peoplesCollectionList.stream().collect(Collectors.flatMapping(p -> p.hobbies.stream(), Collectors.toSet()));
                System.out.print("All unique Hobbies: " + allHobbies); // Outputs: All unique Hobbies: [carrom, basketball, chess, badminton, volleyball, football]

                System.out.println();

                // Flattening nested integer lists and collecting into a set
                List<List<Integer>> listOfIntegerLists = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(1, 2));
                Set<Integer> flattenedSet = listOfIntegerLists.stream().collect(Collectors.flatMapping(List::stream, Collectors.toSet()));
                System.out.print("Flattened Set: " + flattenedSet); // Outputs: Flattened Set: [1, 2, 3, 4]

                System.out.println();

    }
}
