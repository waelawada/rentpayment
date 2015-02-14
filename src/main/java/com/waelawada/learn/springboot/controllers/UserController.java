package com.waelawada.learn.springboot.controllers;

import com.waelawada.learn.springboot.dao.AddressDao;
import com.waelawada.learn.springboot.dao.UserDao;
import com.waelawada.learn.springboot.domain.User;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AddressDao addressDao;

    @RequestMapping(value = "/{id}")
    public String index(@PathVariable Long id) {
        return userDao.findOne(id).toString();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addUser(User user){
        return userDao.save(user).toString();
    }

    @RequestMapping(value = "/{id}/addPaymentMethod", method = RequestMethod.POST)
    public String addPaymentMethodToUser(@PathVariable Long id, PaymentMethod paymentMethod){
        User user = userDao.findOne(id);
        user.getPaymentMethods().add(paymentMethod);
        return userDao.save(user).toString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable Long id, User user){
        User userToBeUpdated = userDao.findOne(id);
        if(user.getAddress() != userToBeUpdated.getAddress()){
            userToBeUpdated.setAddress(user.getAddress());
        }
        if(!user.getEmail().equals(userToBeUpdated.getEmail())){
            userToBeUpdated.setEmail(user.getEmail());
        }
        if(!user.getFirstName().equals(userToBeUpdated.getFirstName())){
            userToBeUpdated.setFirstName(user.getFirstName());
        }
        if(!user.getLastName().equals(userToBeUpdated.getLastName())){
            userToBeUpdated.setLastName(user.getLastName());
        }
        if(!user.getPassword().equals(userToBeUpdated.getPassword())){
            userToBeUpdated.setPassword(user.getPassword());
        }
        return userDao.save(userToBeUpdated).toString();
    }
}