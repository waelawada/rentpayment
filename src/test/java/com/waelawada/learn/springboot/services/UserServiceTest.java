package com.waelawada.learn.springboot.services;

import com.waelawada.learn.springboot.Application;
import com.waelawada.learn.springboot.dao.PaymentMethodDao;
import com.waelawada.learn.springboot.domain.User;
import com.waelawada.learn.springboot.domain.billing.BankAccount;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import com.waelawada.learn.springboot.util.Addresses;
import com.waelawada.learn.springboot.util.Users;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
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

        User user = Users.getUserWithAddressWithPayments();

        User savedUser = userService.save(user);

        assertEquals("Count of rows didn't change", new Long(count+1) , userService.getCount());

        User userInDb = userService.findById(savedUser.getId());
        userInDb.getAddress();
        userInDb.getPaymentMethods();

        assertEquals(savedUser, userService.findById(savedUser.getId()));

    }

    @Test
    @Transactional
    public void testAddingAPaymentMethodToUser(){

        User user = userService.save(Users.getUserWithAddressWithPayments());
        List<PaymentMethod> paymentMethods = user.getPaymentMethods();
        int listSizeBeforeInserting = paymentMethods.size();

        BankAccount bankAccount = new BankAccount();
        bankAccount.setRoutingNumber("234234234");
        bankAccount.setAccountNumber("345345345");

        userService.addPaymentMethodToUser(user, bankAccount);
        paymentMethods = user.getPaymentMethods();
        assertEquals(listSizeBeforeInserting+1 , paymentMethods.size());
    }

    @Test
    @Transactional
    public void testUpdateUsersFirstName(){
        User user = userService.save(Users.getUserWithAddressWithPayments());
        User updatedData = new User();
        updatedData.setFirstName("Test");
        User updatedUser = userService.updateUser(user, updatedData);

        assertEquals(updatedData.getFirstName(), updatedUser.getFirstName());
        assertEquals(user.getLastName(), updatedUser.getLastName());
        assertEquals(user.getAddress(), updatedUser.getAddress());
        assertEquals(user.getEmail(), updatedUser.getEmail());
        assertEquals(user.getPassword(), updatedUser.getPassword());
        assertEquals(user.getPaymentMethods(), updatedUser.getPaymentMethods());
    }

    @Test
    @Transactional
    public void testUpdateUsersAddress(){
        User user = userService.save(Users.getUserWithAddressWithPayments());
        User updatedData = new User();
        updatedData.setAddress(Addresses.getAddress());
        User updatedUser = userService.updateUser(user, updatedData);

        assertEquals(user.getFirstName(), updatedUser.getFirstName());
        assertEquals(user.getLastName(), updatedUser.getLastName());
        assertEquals(updatedData.getAddress(), updatedUser.getAddress());
        assertEquals(user.getEmail(), updatedUser.getEmail());
        assertEquals(user.getPassword(), updatedUser.getPassword());
        assertEquals(user.getPaymentMethods(), updatedUser.getPaymentMethods());
    }


}
