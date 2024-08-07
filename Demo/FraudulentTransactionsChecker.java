package org.program.competitiveCoding;

import java.util.*;

public class FraudulentTransactionsChecker {

    static class Transaction {
        int id;
        int creditCardId;
        double amount;
        String city;
        int time;

        public Transaction(int id, int creditCardId, double amount, String city, int time) {
            this.id = id;
            this.creditCardId = creditCardId;
            this.amount = amount;
            this.city = city;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1, 1000, 500.00, "Vadodara", 0),
                new Transaction(2, 1000, 500.00, "Mumbai", 5),
                new Transaction(3, 1001, 500.00, "Mumbai", 10),
                new Transaction(4, 1001, 10000.00, "Mumbai", 10)
        );
        System.out.println("Fraudulent Transactions IDs: " + findFraudulentTransactions(transactions));
    }

    public static List<Integer> findFraudulentTransactions(List<Transaction> transactions) {
        List<Integer> fraudulentTransactions = new ArrayList<>();
        Map<Integer, Map<String, Integer>> transactionsMap = new HashMap<>();

        for (Transaction transaction : transactions) {
            int id = transaction.id;
            int creditCardId = transaction.creditCardId;
            double amount = transaction.amount;
            String city = transaction.city;
            int time = transaction.time;

            if (amount >= 10000) {
                fraudulentTransactions.add(id);
            }
            transactionsMap.putIfAbsent(creditCardId, new HashMap<>());
            Map<String, Integer> cityTimeMap = transactionsMap.get(creditCardId);

            if (cityTimeMap.containsKey(city)) {
                int previousTime = cityTimeMap.get(city);
                if (time - previousTime <= 30) continue;
            }

            for (Map.Entry<String, Integer> entry : cityTimeMap.entrySet()) {
                String previousCity = entry.getKey();
                int previousTime = entry.getValue();
                if (!previousCity.equals(city) && time - previousTime <= 30) {
                    fraudulentTransactions.add(id);
                    break;
                }
            }
            cityTimeMap.put(city, time);
        }
        return fraudulentTransactions;
    }
}


