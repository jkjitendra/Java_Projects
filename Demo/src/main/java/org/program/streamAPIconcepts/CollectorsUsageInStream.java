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
    }
}
