package com.waelawada.learn.springboot.util;

import com.waelawada.learn.springboot.domain.billing.BankAccount;
import com.waelawada.learn.springboot.domain.billing.CreditCard;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import org.fluttercode.datafactory.impl.DataFactory;

/**
 * Created by waelawada on 2/14/15.
 */
public class PaymentMethods {

    public static DataFactory dataFactory;

    public static PaymentMethod getCreditCard(){
        CreditCard.Builder creditCardBuilder = CreditCard.newBuilder();
        creditCardBuilder.creditCardNumber(RandomCreditCardNumberGenerator.credit_card_number(RandomCreditCardNumberGenerator.AMEX_PREFIX_LIST, 13, 1)[0]);
        int randomMonth = (int)(Math.random()*12)+1;
        int randomYear = (int)Math.random()*40;

        creditCardBuilder.expirationDate((randomMonth < 10 ? "0" + randomMonth : "" + randomMonth) + (randomYear < 10 ? "0" + randomYear : "" + randomYear));

        int randomCvv = (int)Math.random()*1000;
        creditCardBuilder.cvv2((randomCvv < 10 ? "00" + randomCvv : (
                randomCvv < 100 ? "0" + randomCvv : "" + randomCvv
        )));
        creditCardBuilder.billingAddress(Addresses.getAddress());
        return creditCardBuilder.build();
    }


    public static PaymentMethod getBankAccount(){
        BankAccount.Builder bankAccountBuilder = BankAccount.newBuilder();
        bankAccountBuilder.accountNumber(String.valueOf((int) Math.random() * 1000000000));
        bankAccountBuilder.routingNumber(String.valueOf((int)Math.random()*1000000000));
        return bankAccountBuilder.build();
    }

    public static PaymentMethod getRandomPaymentMethod(){
        int choice = (int)Math.random()*2;
        if(choice==0){
            return getCreditCard();
        }
        else{
            return getBankAccount();
        }
    }


}
