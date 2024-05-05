package org.program.streamAPIconcepts.prepare;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionMonitor {
    String customerId;
    Date transactionDate;
    double amount;

    public TransactionMonitor(String customerId, Date transactionDate, double amount) {
        this.customerId = customerId;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public double getAmount() {
        return amount;
    }

}
