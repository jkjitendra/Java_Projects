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

    }
}
