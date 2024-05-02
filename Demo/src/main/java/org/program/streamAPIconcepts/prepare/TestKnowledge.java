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

    }
}
