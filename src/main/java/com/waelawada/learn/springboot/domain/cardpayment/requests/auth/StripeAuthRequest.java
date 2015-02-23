package com.waelawada.learn.springboot.domain.cardpayment.requests.auth;

import com.waelawada.learn.springboot.domain.users.User;
import com.waelawada.learn.springboot.domain.billing.CreditCard;

import java.util.Map;

/**
 * Created by waelawada on 2/15/15.
 */
public class StripeAuthRequest extends GenericAuthRquest {

    private int id;
    private String transactionId;
    private Double amount;
    private String currency = "USD";
    private CreditCard creditCard;
    private User user;
    private String description;
    private String statementDescriptor;
    private Map<String, String> metaData;
    private boolean capture = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatementDescriptor() {
        return statementDescriptor;
    }

    public void setStatementDescriptor(String statementDescriptor) {
        this.statementDescriptor = statementDescriptor;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }

    public void setMetaData(Map<String, String> metaData) {
        this.metaData = metaData;
    }

    public boolean isCapture() {
        return capture;
    }

    public void setCapture(boolean capture) {
        this.capture = capture;
    }
}
