package com.waelawada.learn.springboot.domain.billing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by waelawada on 2/12/15.
 */
@Entity
@DiscriminatorValue("bank_account")
@Table(name = "bank_account")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BankAccount extends PaymentMethod {

    private String routingNumber;
    private String accountNumber;

    private BankAccount(Builder builder) {
        setRoutingNumber(builder.routingNumber);
        setAccountNumber(builder.accountNumber);
        setId(builder.id);
    }

    public BankAccount() {
    }

    public static Builder newBuilder() {
        return new Builder();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount)) return false;

        BankAccount that = (BankAccount) o;

        if (accountNumber != null ? !accountNumber.equals(that.accountNumber) : that.accountNumber != null)
            return false;
        if (routingNumber != null ? !routingNumber.equals(that.routingNumber) : that.routingNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = routingNumber != null ? routingNumber.hashCode() : 0;
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        return result;
    }


    public static final class Builder {
        private String routingNumber;
        private String accountNumber;
        private Long id;

        private Builder() {
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder routingNumber(String routingNumber) {
            this.routingNumber = routingNumber;
            return this;
        }

        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public BankAccount build() {
            return new BankAccount(this);
        }
    }
}
