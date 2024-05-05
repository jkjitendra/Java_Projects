package org.program.streamAPIconcepts.prepare;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestKnowledge {
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

    }
}
