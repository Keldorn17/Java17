package main.java.com.keldorn.model.bank.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record Transaction(int routingNumber, UUID transactionId, UUID customerId, BigDecimal transactionAmount) {
    @Override
    public String toString() {
        return "Transaction{" +
                "routingNumber=" + routingNumber +
                ", transactionId=" + transactionId +
                ", customerId=" + customerId +
                ", transactionAmount=" + transactionAmount +
                '}';
    }
}
