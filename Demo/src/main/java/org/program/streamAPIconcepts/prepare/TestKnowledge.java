package org.program.streamAPIconcepts.prepare;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestKnowledge {

    private static final WebClient webClient = WebClient.create("https://www.alphavantage.co");
    
    public static void main(String[] args) {
    //  Basic to Intermediate Questions
        // Data Filtering and Collection:
           /* Given a list of transactions, write a stream operation to find all transactions in the year 2020
              and return them sorted by value (ascending).*/
            List<Transaction> transactions = Arrays.asList(
                    new Transaction(2019, 200),
                    new Transaction(2020, 150),
                    new Transaction(2020, 300),
                    new Transaction(2021, 450)
            );
            List<Transaction> filteredTransactions = transactions.stream()
                    .filter(transaction -> transaction.getYear() == 2020)
                    .sorted(Comparator.comparing(Transaction::getValue))
                    .collect(Collectors.toList());
            filteredTransactions.forEach(transaction -> System.out.println(transaction.getYear() + " " + transaction.getValue()));

        // Data Transformation:
           /* You have a list of customer objects. Each customer has a name, age, and a list of accounts,
              where each account holds a balance. Use streams to produce a list of all customer names
              who have a total balance over $50,000.*/
            List<Customer> customers = Arrays.asList(
                    new Customer("Alice", 30, Arrays.asList(new Account(30000))),
                    new Customer("Bob", 45, Arrays.asList(new Account(10000), new Account(45000))),
                    new Customer("Charlie", 25, Arrays.asList(new Account(51000)))
            );

            List<Customer> customerList = customers.stream()
                    .filter(customer -> customer.getTotalBalance() > 50000)
                    .collect(Collectors.toList());
            customerList.forEach(customer -> System.out.print(customer.getName() + " "));

        // Aggregation:
           /* How would you use streams to calculate the average age of all customers who have at least one account with over $10,000.*/
            List<Customer> customersAggregationList = Arrays.asList(
                    new Customer("Alice", 30, Arrays.asList(new Account(12000))),
                    new Customer("Bob", 40, Arrays.asList(new Account(2000), new Account(3000))),
                    new Customer("Charlie", 20, Arrays.asList(new Account(7000), new Account(8000), new Account(12000)))
            );
            double averageAge = customersAggregationList.stream()
                            .filter(customer -> customer.getAccounts().stream().anyMatch(account -> account.getBalance() > 10000))
                            .mapToInt(Customer::getAge)
                            .average()
                            .orElse(0);
            System.out.print(averageAge);

            // Outputs: 25.0

            System.out.println();

        /* How would you use streams to calculate the average age of all customers who have over $10,000 in all accounts. */
            List<Customer> customersAggregationsList = Arrays.asList(
                    new Customer("Alice", 30, Arrays.asList(new Account(12000))),
                    new Customer("Bob", 40, Arrays.asList(new Account(20000), new Account(30000))),
                    new Customer("Charlie", 20, Arrays.asList(new Account(7000), new Account(8000), new Account(12000)))
            );
            double averageAgeOfCustomer = customersAggregationsList.stream()
                    .filter(customer -> customer.getAccounts().stream().allMatch(account -> account.getBalance() > 10000))
                    .mapToInt(Customer::getAge)
                    .average()
                    .orElse(0);
            System.out.print(averageAgeOfCustomer);

            // Outputs: 35.0

            System.out.println();

        // Grouping Data:
           /* Explain how you would use streams to group a list of sales by product category and sum up the total sales amount per category. */
            List<Sale> sales = Arrays.asList(
                    new Sale("Electronics", 299.99),
                    new Sale("Electronics", 199.99),
                    new Sale("Books", 39.99),
                    new Sale("Books", 12.99)
            );

            Map<String, List<Sale>> salesGroupByCategory = sales.stream().collect(Collectors.groupingBy(Sale::getCategory));
            salesGroupByCategory.forEach((category, categorizedSalesList) -> {
                System.out.print(category + ": ");
                System.out.println(categorizedSalesList.stream().mapToDouble(Sale::getAmount).sum());

            });

            // Outputs: Electronics: 499.98
            //          Books: 52.980000000000004


            // OR

            Map<String, Double> totalSalesByCategory = sales.stream()
                    .collect(Collectors.groupingBy(
                            Sale::getCategory,
                            Collectors.summingDouble(Sale::getAmount)
                    ));

            totalSalesByCategory.forEach((category, totalAmount) -> {
                System.out.println(category + ": " + totalAmount);
            });

            // Outputs: Electronics: 499.98
            //          Books: 52.980000000000004

            System.out.println();

        // Finding Data:
            /* Write a stream operation to find any customer who has an account balance exactly equal to $5,000. Assume you have a list of customers
               and each customer has a list of accounts.*/
            List<Customer> customersFind = Arrays.asList(
                    new Customer("Alice", 30, Arrays.asList(new Account(5000), new Account(15000))),
                    new Customer("Bob", 45, Arrays.asList(new Account(3000))),
                    new Customer("Charlie", 25, Arrays.asList(new Account(5000), new Account(7000)))
            );
            String customerString = customersFind.stream()
                .filter(cust -> cust.getAccounts().stream().anyMatch(account -> account.getBalance() == 5000))
                .map(Customer::getName)
                .collect(Collectors.joining(", "));
            System.out.print(customerString);

            // Outputs: Alice, Charlie

            System.out.println();

            //OR

            Optional<Customer> anyCustomerWithExactBalance = customersFind.stream()
                    .filter(cust -> cust.getAccounts().stream()
                    .anyMatch(account -> account.getBalance() == 5000))
                    .findAny();
            anyCustomerWithExactBalance.ifPresent(customer -> System.out.print(customer.getName()));

            // Outputs: Alice

            System.out.println();

        // Advanced Questions
        // Complex Reductions:
            /* Discuss how you would use streams to create a map where each key is a customer age group (e.g., "Under 18", "18-65", "Above 65")
            and the value is the average account balance for customers in that age group. */
            List<Customer> customersComplexReduction = Arrays.asList(
                    new Customer("Alice", 17, Arrays.asList(new Account(500))),
                    new Customer("Bob", 23, Arrays.asList(new Account(5000))),
                    new Customer("Charlie", 70, Arrays.asList(new Account(7000)))
            );
            final String UNDER_18 = "Under 18";
            final String BETWEEN_18_AND_65 = "18-65";
            final String ABOVE_65 = "Above 65";

            Map<String, Double> customerStream = customersComplexReduction.stream()
                .collect(Collectors.groupingBy(customer -> {
                    int age = customer.getAge();
                    if (age < 18) return UNDER_18;
                    else if (age <= 65) return BETWEEN_18_AND_65;
                    else return ABOVE_65;
                }, Collectors.averagingDouble(customer -> customer.getAccounts().stream().mapToDouble(Account::getBalance).sum())
            ));
            customerStream.forEach((ageGroup, avgBalance) -> {
                System.out.printf("%s: %.2f%n", ageGroup, avgBalance);
            });

            // Outputs:
            //  Above 65: 7000.00
            //  Under 18: 500.00
            //  18-65: 5000.00
        
            System.out.println();

        // Concurrency and Parallel Streams:
            /* How would you modify a stream processing operation to use parallel streams to speed up processing a large dataset,
               and what considerations should you take into account when doing so? */
            long startTimeParallel = System.nanoTime();
            System.out.println("start time: " + startTimeParallel);
            List<Integer> largeDataset = new Random().ints(1, 40).limit(120).boxed().collect(Collectors.toList());
            List<Integer> processedLargeDatasetParallely = largeDataset.parallelStream()
                    .mapToInt(TestKnowledge::computeExpensiveOperation)  // Hypothetical computationally expensive operation (Check prime and return the number which is divisible by
                    .boxed()
                    .collect(Collectors.toList());
            processedLargeDatasetParallely.forEach(System.out::println);
            double endTime = (double) (System.nanoTime() - startTimeParallel) / 1_000_000;
            System.out.println("end time: " + endTime / 1000);

            // Outputs: List of fibonacci numbers generated for numbers present in the largeDataset

//            long startTimeSequence = System.nanoTime();
//            System.out.println("start time: " + startTimeSequence);
//            List<Integer> processedLargeDatasetSequencially = largeDataset.stream()
//                    .mapToInt(TestKnowledge::computeExpensiveOperation)  // Hypothetical computationally expensive operation (Check prime and return the number which is divisible by
//                    .boxed()
//                    .collect(Collectors.toList());
//            processedLargeDatasetSequencially.forEach(System.out::println);
//            double endTimeSequence = (double) (System.nanoTime() - startTimeSequence) /1_000_000;
//            System.out.println("end time: " + endTimeSequence/1000);

        // Custom Collector:
           /* Can you devise a custom collector that takes a stream of numbers and returns a custom statistics object that
              includes the sum, product, minimum, maximum, and average of these numbers? */
            List<Integer> numbers = Arrays.asList(2, 7, 3, 9, 5);
            CustomStatisticsCollector.CustomStatistics stats = numbers.stream().collect(new CustomStatisticsCollector());
            System.out.print(stats); // Outputs: CustomStatistics{count=5, sum=26, product=1890, min=2, average=5.200000, max=9}

            System.out.println();

        // Data Joining and Stream Operations:
           /* Assume you have two lists: one of Order objects and one of Product objects. Each Order includes an order ID and product ID,
              while each Product includes a product ID and price. Write a stream expression to calculate the total revenue (sum of all product prices per order)
              for completed orders. */
            List<Order> orders = Arrays.asList(
                    new Order(1, "A123", OrderStatus.COMPLETED),
                    new Order(2, "B456", OrderStatus.COMPLETED),
                    new Order(3, "A123", OrderStatus.PENDING),
                    new Order(4, "B123", OrderStatus.COMPLETED)
            );
            List<Product> products = Arrays.asList(
                    new Product("A123", 19.99),
                    new Product("B456", 29.99),
                    new Product("B123", 23.99)
            );
            double totalRevenue = orders.stream()
                    .filter(order -> order.getStatus() == OrderStatus.COMPLETED)
                    .mapToDouble(orderForProduct -> products.stream()
                            .filter(product -> product.getProductId().equals(orderForProduct.getProductId()))
                            .mapToDouble(Product::getPrice)
                            .sum()
                    ).sum();

            System.out.print("Total Revenue for Completed Orders: " + totalRevenue); // Outputs: Total Revenue for Completed Orders: 73.97

            System.out.println();

            Map<String, Double> productPriceMap = products.stream()
                    .collect(Collectors.toMap(Product::getProductId, Product::getPrice));
            double totalRevenueOfCompletedOrders = orders.stream()
                    .filter(order -> order.getStatus() == OrderStatus.COMPLETED)
                    .mapToDouble(order -> productPriceMap.getOrDefault(order.getProductId(), 0.0))
                    .sum();

            System.out.print("Total Revenue for Completed Orders: " + totalRevenueOfCompletedOrders); // Outputs: Total Revenue for Completed Orders: 73.97

            System.out.println();

        // Advanced Filtering and Optional:
           /* Using a stream of Employee objects, where each Employee has fields for job title and salary, find the highest salary of an "Engineer".
              Return this value wrapped in an Optional for safe processing. */
            List<Employee> employees = Arrays.asList(
                    new Employee("Engineer", 80000),
                    new Employee("Engineer", 90000),
                    new Employee("Manager", 95000)
            );
            OptionalDouble maxSal = employees.stream()
                    .filter(employee -> employee.getJobTitle().equals("Engineer"))
                    .mapToDouble(Employee::getSalary)
                    .max();
            maxSal.ifPresent(value -> System.out.print("Maximum Salary among Engineers: " + value)); // Outputs: Maximum Salary among Engineers: 90000.0

            System.out.println();

        //Real-World Scenario-Based Questions
        // Efficient Data Processing:
           /* Given a real-time stream of stock market price updates, explain how you would use streams to filter out updates for a particular stock symbol
              and calculate a moving average. */

            //  Bounded stream values
            Stream<StockUpdate> stockUpdates = Stream.of(
                    new StockUpdate("AAPL", 150.25),
                    new StockUpdate("GOOGL", 1200.75),
                    new StockUpdate("AAPL", 151.00)
            );
            OptionalDouble movingAvg = stockUpdates
                    .filter(stockUpdate -> stockUpdate.getSymbol().equals("AAPL"))
                    .mapToDouble(StockUpdate::getPrice)
                    .average();
            movingAvg.ifPresent(value -> System.out.print("Moving average: " + value)); // Outputs: Moving average: 150.625

            System.out.println();

            // real-time moving average using Flux
            final int bufferSize = 10; // Size of the window for the moving average
            Queue<Double> priceBuffer = new ArrayDeque<>(bufferSize);
            String stockSymbol = "IBM";
            Flux.interval(Duration.ofSeconds(3)) // Adjust the interval as needed
                    .flatMap(tick -> fetchStockPrice(stockSymbol))
                    .map(price -> {
                        if (priceBuffer.size() == bufferSize) {
                            priceBuffer.poll(); // Remove the oldest price
                        }
                        priceBuffer.offer(price); // Add the latest price
                        return priceBuffer.stream().mapToDouble(Double::doubleValue).average().orElse(0.0); // Calculate the average
                    })
                    .subscribe(average -> System.out.println("Moving average in real-time: " + average)); // Print the moving average

            try {
                Thread.sleep(60000); // Let the flux emit for 60 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }

        // Data Cleansing:
           /* Describe a process using Java streams to read a large CSV file of user data, filter out rows with missing values, transform/cleanse data formats
              (like dates and phone numbers), and collect results into a new CSV file. */
            List<String> csvRows = Arrays.asList(
                    "UserID,Name,DateOfBirth,PhoneNumber", // This is the header
                    "1,John Doe,1985-02-15,555-3421",
                    "2,Jane Smith,1990-11-01,555-7102",
                    "3,Bob Johnson,,555-6403",
                    "4,Lisa White,1987-07-23",
                    "5,Alex Green,1992-03-12,555-9821",
                    "6,Sam Blue,1989-06-09,555-4532",
                    "7,Kate Black,1991-08-19,555-7845"
            );
//            try (FileWriter writer = new FileWriter("output.csv")) {
//                boolean firstLine = true;
//                for (String row : csvRows) {
//                    if (firstLine) {
//                        writer.write(row + "\n"); // Write the header
//                        firstLine = false;
//                    } else {
//                        writer.write(row + "\n"); // Write the data rows
//                    }
//                }
//                writer.flush();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            String inputPath = "output.csv";
            String outputPath = "cleaned_data.csv";
            try (CSVReader reader = new CSVReader(new FileReader(inputPath));
                 CSVWriter writer = new CSVWriter(new FileWriter(outputPath))) {
                // Skip header
                String[] headers = reader.readNext();
                writer.writeNext(headers);

                List<String[]> filteredAndTransformed = reader.readAll().stream()
                        .filter(row -> row.length == 4 && !anyFieldEmpty(row))
                        .map(TestKnowledge::transformRow)
                        .collect(Collectors.toList());

                writer.writeAll(filteredAndTransformed);
                System.out.println("Data Cleansed and stored in " + outputPath);

            }
            catch (CsvException | IOException e) {
                e.printStackTrace();
            }

        // Business Rule Implementation:
           /* You have a continuous stream of e-commerce transactions. How would you implement a system using Java streams to detect and
              alert if the total transaction amount for a single customer in a single day exceeds a threshold, e.g., $10,000? */
            List<TransactionMonitor> transactionsStream = new ArrayList<>();
            // Simulate transactions for various customers on the same day
            transactionsStream.add(new TransactionMonitor("Cust1", new Date(), 5000));
            transactionsStream.add(new TransactionMonitor("Cust1", new Date(), 7000));
            transactionsStream.add(new TransactionMonitor("Cust2", new Date(), 2000));
            transactionsStream.add(new TransactionMonitor("Cust1", new Date(), 500));
            transactionsStream.add(new TransactionMonitor("Cust2", new Date(), 4000));
            transactionsStream.add(new TransactionMonitor("Cust3", new Date(), 200));

            final double THRESHOLD = 10000.0;
            // Group by customer and date, then sum amounts
            Map<String, Double> totalAmountOfEachCustomer = transactionsStream.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getCustomerId() + " " + t.getTransactionDate(),
                        Collectors.summingDouble(TransactionMonitor::getAmount)
                ));

            // Check if threshold exceeds
            totalAmountOfEachCustomer.forEach((key, total) -> {
                if (total > THRESHOLD) {
                    System.out.println("Alert: Customer " + Arrays.stream(key.split(" ")).findFirst().orElse("") + " exceeded the threshold with " + total);
                }
            });

    }

    private static int computeExpensiveOperation(int value) {
        // Calculate Fibonacci number for each value (naively)
        if (value <= 1) return value;
        else return computeExpensiveOperation(value-1) + computeExpensiveOperation(value-2);
    }

    private static Mono<Double> fetchStockPrice(String symbol) {
        
        // Real API call
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/query")
                .queryParam("function", "TIME_SERIES_INTRADAY")
                .queryParam("symbol", symbol)
                .queryParam("interval", "5min")
                .queryParam("apikey", "1SAO55O47OAIS500")
                .build()
            )
            .retrieve()
            .bodyToMono(StockPriceResponse.class)
            .map(response -> {
                // Proceed with extracting and processing the data
                Map<String, Map<String, String>> timeSeries = response.getTimeSeries();
                Optional<String> mostRecentEntry = timeSeries.keySet().stream()
                    .sorted(Comparator.reverseOrder()) // Sort the keys in reverse order (newest first)
                    .findFirst();
                return mostRecentEntry
                    .map(timeKey -> Double.parseDouble(timeSeries.get(timeKey).get("4. close"))) // Parse the closing price
                    .orElse(0.0);
            })
            .onErrorResume(e -> {
                System.err.println("Error fetching stock price: " + e.getMessage());
                return Mono.just(0.0); // Return a default value in case of error
            });
    }

    private static boolean anyFieldEmpty(String[] row) {
        for (String field : row) {
            if (field == null || field.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static String[] transformRow(String[] row) {
        // Assuming DateOfBirth is in the format yyyy-MM-dd and needs no change
        // PhoneNumber might be formatted or validated here
        String formattedPhone = row[3].replaceAll("-", "");
        formattedPhone = "+91" + formattedPhone; // Assuming Indian numbers for simplicity

        return new String[]{row[0], row[1], row[2], formattedPhone};
    }

}
