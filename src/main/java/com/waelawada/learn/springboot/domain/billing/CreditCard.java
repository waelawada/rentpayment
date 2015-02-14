package com.waelawada.learn.springboot.domain.billing;

import com.waelawada.learn.springboot.domain.Address;

import javax.persistence.*;

/**
 * Created by waelawada on 2/12/15.
 */
@Entity
@DiscriminatorValue("credit_card")
@Table(name = "credit_card")
public class CreditCard extends PaymentMethod {

    private String creditCardNumber;
    private String expirationDate;
    private String cvv2;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address billingAddress;
    private String token;

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
}
