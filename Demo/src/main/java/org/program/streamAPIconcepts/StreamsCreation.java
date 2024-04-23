package org.program.streamAPIconcepts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

public class StreamsCreation {

    //    Streams have intermediate and terminal operations which we can use as per our requirement. Here’s a list of Operations:
    //    Streams doesn’t store the data. You can’t add or remove elements from streams. Hence, they are not the data structures. They are the just operations on data.
    //    Stream consumes a source, performs operations on it and produces the result. Source may be a collection or an array or an I/O resource. Remember, stream doesn’t modify the source.
    //    Most of the stream operations return another new stream, and they can be chained together to form a pipeline of operations.

    //    1. Intermediate Operations: peek, flatMap, limit, sort, distinct, map, filter etc.
    //    2. Terminal Operations: noneMatch, allMatch, collect, findAny, findFirst, anyMatch, toArray, reduce, min, max, forEach, collect, count etc.

    public static void main(String[] args) {
        // Creating Streams from Collections
        List<String> list = Arrays.asList("Apple", "Banana", "Cherry", "Date");
        Stream<String> streamFromList = list.stream();
        streamFromList.forEach(System.out::print);
        System.out.println();

        // Creating Streams from Arrays
        String[] array = new String[]{"Apple", "Banana", "Cherry", "Date"};
        Stream<String> streamFromArray = Arrays.stream(array);
        streamFromArray.forEach(System.out::print);
        System.out.println();

        // Creating Streams from Static Factory Methods
        Stream<String> streamOfStrings = Stream.of("Apple", "Banana", "Cherry", "Date");
        streamOfStrings.forEach(System.out::print);
        System.out.println();

        // For primitive types
        IntStream streamOfInts = IntStream.of(1, 2, 3, 4);
        streamOfInts.forEach(System.out::print);
        System.out.println();

        DoubleStream streamOfDoubles = DoubleStream.of(1, 2, 3, 4);
        streamOfDoubles.forEach(System.out::print);
        System.out.println();

        // Using range
        IntStream intRangeStream = IntStream.range(1, 5); // Generates numbers from 1 to 4
        intRangeStream.forEach(System.out::print);
        System.out.println();

        //  Creating Streams from I/O Channels
        // One common use case is to create a stream from the lines of a file, which can be done using Files.lines(Path path)
        Path path = Paths.get("src/main/java/org/program/TrafficSignalSimple.java"); // Make sure to have this file in your working directory with some text.
        // This method opens a file, reads all lines lazily, and creates a stream allowing you to process the file line by line.
        // It's important to note that the Files.lines() method needs to be closed, which is why it is used within a try-with-resources statement here.
        try (Stream<String> lines = Files.lines(path)) { //
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // To print comma separated values use

        // Collectors.joining
        IntStream rangeStream1 = IntStream.range(1, 5);
        String result = rangeStream1.mapToObj(String::valueOf)
                                    .collect(Collectors.joining(", "));
        System.out.println(result); // Outputs: 1, 2, 3, 4

        // or StringBuilder
        StringBuilder sb = new StringBuilder();
        IntStream rangeStream2 = IntStream.range(1, 5);
        rangeStream2.forEach(value -> sb.append(value).append(", "));
        if (sb.length() > 0) sb.setLength(sb.length() - 2); // Remove the last comma and space
        System.out.println(sb.toString()); // Outputs: 1, 2, 3, 4
    }
}
