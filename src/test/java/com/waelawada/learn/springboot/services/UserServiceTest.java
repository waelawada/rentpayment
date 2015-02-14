package com.waelawada.learn.springboot.services;

import com.waelawada.learn.springboot.Application;
import com.waelawada.learn.springboot.dao.PaymentMethodDao;
import com.waelawada.learn.springboot.dao.UserDao;
import com.waelawada.learn.springboot.domain.Address;
import com.waelawada.learn.springboot.domain.User;
import com.waelawada.learn.springboot.domain.billing.BankAccount;
import com.waelawada.learn.springboot.domain.billing.CreditCard;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by waelawada on 2/13/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    private PaymentMethodDao paymentMethodDao;

    @Test
    public void testSaveUser(){

        User user = new User();
        user.setFirstName("Wael");
        user.setLastName("Awada");
        user.setPassword("password");
        user.setEmail("waelawada@gmail.com");


        Address address = new Address();
        address.setStreetAddress("1908 NW 96th Ave");
        address.setCity("Beirut");
        address.setState("GA");
        address.setZipCode("30908");
        address.setCountry("US");

        user.setAddress(address);

        BankAccount paymentMethod = new BankAccount();
        paymentMethod.setAccountNumber("1234567890");
        paymentMethod.setRoutingNumber("123123123");
        paymentMethodDao.save(paymentMethod);

        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber("1234567890123456");
        creditCard.setExpirationDate("0515");
        creditCard.setCvv2("019");
        creditCard.setToken("123456789");
        creditCard.setBillingAddress(address);


        List<PaymentMethod> paymentMethods = Arrays.asList((PaymentMethod)paymentMethod);
        user.setPaymentMethods(paymentMethods);

        userService.save(user);


        System.out.println("TEST NOW");


    }


}
