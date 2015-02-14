package com.waelawada.learn.springboot.domain.billing;

import javax.persistence.*;

/**
 * Created by waelawada on 2/12/15.
 */
@Entity
@DiscriminatorValue("bank_account")
@Table(name = "bank_account")
public class BankAccount extends PaymentMethod {

    private String routingNumber;
    private String accountNumber;

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                ", routingNumber='" + routingNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
