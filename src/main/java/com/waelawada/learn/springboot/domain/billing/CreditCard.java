package com.waelawada.learn.springboot.domain.billing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.waelawada.learn.springboot.domain.Address;

import javax.persistence.*;

/**
 * Created by waelawada on 2/12/15.
 */
@Entity
@DiscriminatorValue("credit_card")
@Table(name = "credit_card")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CreditCard extends PaymentMethod {

    private String creditCardNumber;
    private String expirationDate;
    private String cvv2;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address billingAddress;
    private String token;

    private CreditCard(Builder builder) {
        setCreditCardNumber(builder.creditCardNumber);
        setExpirationDate(builder.expirationDate);
        setCvv2(builder.cvv2);
        setBillingAddress(builder.billingAddress);
        setToken(builder.token);
        setId(builder.id);
    }

    public CreditCard() {
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "token='" + token + '\'' +
                ", billingAddress=" + billingAddress +
                ", cvv2='" + cvv2 + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard)) return false;

        CreditCard that = (CreditCard) o;

        if (billingAddress != null ? !billingAddress.equals(that.billingAddress) : that.billingAddress != null)
            return false;
        if (creditCardNumber != null ? !creditCardNumber.equals(that.creditCardNumber) : that.creditCardNumber != null)
            return false;
        if (cvv2 != null ? !cvv2.equals(that.cvv2) : that.cvv2 != null) return false;
        if (expirationDate != null ? !expirationDate.equals(that.expirationDate) : that.expirationDate != null)
            return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = creditCardNumber != null ? creditCardNumber.hashCode() : 0;
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        result = 31 * result + (cvv2 != null ? cvv2.hashCode() : 0);
        result = 31 * result + (billingAddress != null ? billingAddress.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }


    public static final class Builder {
        private String creditCardNumber;
        private String expirationDate;
        private String cvv2;
        private Address billingAddress;
        private String token;
        private Long id;

        private Builder() {
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder creditCardNumber(String creditCardNumber) {
            this.creditCardNumber = creditCardNumber;
            return this;
        }

        public Builder expirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public Builder cvv2(String cvv2) {
            this.cvv2 = cvv2;
            return this;
        }

        public Builder billingAddress(Address billingAddress) {
            this.billingAddress = billingAddress;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public CreditCard build() {
            return new CreditCard(this);
        }
    }
}
