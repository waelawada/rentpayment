package com.waelawada.learn.springboot.dto.payments;

import com.waelawada.learn.springboot.domain.Address;

/**
 * Created by waelawada on 2/21/15.
 */
public class ResidentCreditCardPaymentMethodDto extends ResidentPaymentMethodDto {

    private String creditCardNumber;
    private String expirationDate;
    private String cvv2;
    private Address billingAddress;
    private String token;

}
