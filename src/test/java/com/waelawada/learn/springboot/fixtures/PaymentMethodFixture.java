package com.waelawada.learn.springboot.fixtures;

import com.waelawada.learn.springboot.domain.billing.BankAccount;
import com.waelawada.learn.springboot.domain.billing.CreditCard;
import com.waelawada.learn.springboot.domain.users.ResidentUser;

/**
 * Created by waelawada on 2/28/15.
 */
public class PaymentMethodFixture {

    public final static CreditCard CREDIT_CARD_1 = CreditCard.newBuilder()
            .creditCardNumber("4222222222222222").expirationDate("1016").cvv2("123")
            .billingAddress(AddressFixtures.FL_ADDRESS_1).setId(1L).token("qwerasdf")
            .build();

    public final static CreditCard CREDIT_CARD_2 = CreditCard.newBuilder()
            .creditCardNumber("4123123412341234").expirationDate("0719").cvv2("321")
            .billingAddress(AddressFixtures.FL_ADDRESS_2).setId(2L).token("azsxdcfv")
            .build();

    public final static CreditCard CREDIT_CARD_3 = CreditCard.newBuilder()
            .creditCardNumber("5432432143214321").expirationDate("1019").cvv2("631")
            .billingAddress(AddressFixtures.GA_ADDRESS_1).setId(3L).token("oknijbuh")
            .build();

    public final static BankAccount BANK_ACCOUNT_1 = BankAccount.newBuilder()
            .accountNumber("123456789").routingNumber("987654321").setId(4L).build();

    public final static BankAccount FULL_BANK_ACCOUNT_2 = BankAccount.newBuilder()
            .accountNumber("234512375").routingNumber("762130935").setId(5L).build();

    public final static BankAccount FULL_BANK_ACCOUNT_3 = BankAccount.newBuilder()
            .accountNumber("721547804").routingNumber("254189047").setId(6L).build();



}
