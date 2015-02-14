package com.waelawada.learn.springboot.services;

import com.waelawada.learn.springboot.Application;
import com.waelawada.learn.springboot.dao.PaymentMethodDao;
import com.waelawada.learn.springboot.dao.UserDao;
import com.waelawada.learn.springboot.domain.Address;
import com.waelawada.learn.springboot.domain.User;
import com.waelawada.learn.springboot.domain.billing.BankAccount;
import com.waelawada.learn.springboot.domain.billing.CreditCard;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
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

    private Long count;

    @Before
    public void setup(){
        count = userService.getCount();
    }


    @Test
    @Transactional
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

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber("1234567890");
        bankAccount.setRoutingNumber("123123123");
        paymentMethodDao.save(bankAccount);

        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber("1234567890123456");
        creditCard.setExpirationDate("0515");
        creditCard.setCvv2("019");
        creditCard.setToken("123456789");
        creditCard.setBillingAddress(address);
        paymentMethodDao.save(creditCard);

        List<PaymentMethod> paymentMethods = Arrays.asList(bankAccount, creditCard);
        user.setPaymentMethods(paymentMethods);

        User savedUser = userService.save(user);

        assertEquals("Count of rows didn't change", new Long(count+1) , userService.getCount());

        User userInDb = userService.findById(savedUser.getId());
        userInDb.getAddress();
        userInDb.getPaymentMethods();

        assertEquals(savedUser, userService.findById(savedUser.getId()));

    }


}
