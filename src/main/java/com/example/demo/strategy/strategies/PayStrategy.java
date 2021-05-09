package com.example.demo.strategy.strategies;

/*
 * Common interface for all strategies
 */
public interface PayStrategy {

    boolean pay(int paymentAmount);
    void collectPaymentDetails();

}
