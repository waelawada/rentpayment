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
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(RandomCreditCardNumberGenerator.credit_card_number(RandomCreditCardNumberGenerator.AMEX_PREFIX_LIST, 13, 1)[0]);
        int randomMonth = (int)(Math.random()*12)+1;
        int randomYear = (int)Math.random()*40;

        creditCard.setExpirationDate((randomMonth<10?"0"+randomMonth:""+randomMonth)+(randomYear<10?"0"+randomYear:""+randomYear));

        int randomCvv = (int)Math.random()*1000;
        creditCard.setCvv2((randomCvv<10? "00"+randomCvv : (
                randomCvv<100? "0"+randomCvv: ""+randomCvv
                )));
        creditCard.setBillingAddress(Addresses.getAddress());
        return creditCard;
    }


    public static PaymentMethod getBankAccount(){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(String.valueOf((int) Math.random() * 1000000000));
        bankAccount.setRoutingNumber(String.valueOf((int)Math.random()*1000000000));
        return bankAccount;
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
