package com.waelawada.learn.springboot.util;

import com.waelawada.learn.springboot.domain.Address;
import com.waelawada.learn.springboot.domain.User;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by waelawada on 2/14/15.
 */
public class Users {

    public static DataFactory dataFactory = new DataFactory();

    public static User getUserWithoutAddressWithoutPayments(){
        User user = new User();
        user.setFirstName(dataFactory.getFirstName());
        user.setLastName(dataFactory.getLastName());
        user.setPassword(dataFactory.getRandomChars(10));
        user.setEmail(dataFactory.getEmailAddress());
        return user;
    }

    public static User getUserWithAddressWithoutPayments(){
        User user = getUserWithoutAddressWithoutPayments();

        Address address = new Address();
        address.setCountry("US");
        address.setStreetAddress(dataFactory.getAddress());
        address.setCountry(dataFactory.getCity());
        address.setState(dataFactory.getRandomChars(2));
        address.setZipCode(dataFactory.getNumberText(5));

        user.setAddress(address);
        return user;
    }

    public static User getUserWithAddressWithPayments(){
        User user = getUserWithAddressWithoutPayments();
        PaymentMethod paymentMethod = PaymentMethods.getRandomPaymentMethod();
        user.setPaymentMethods(new LinkedList<PaymentMethod>(Arrays.asList(paymentMethod)));
        return user;
    }

}
