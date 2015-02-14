package com.waelawada.learn.springboot.controllers;

import com.waelawada.learn.springboot.dao.AddressDao;
import com.waelawada.learn.springboot.dao.UserDao;
import com.waelawada.learn.springboot.domain.User;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import com.waelawada.learn.springboot.services.UserService;
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
    private UserService userService;

    @RequestMapping(value = "/{id}")
    public User index(@PathVariable Long id) {
        return userService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User addUser(User user){
        return userService.save(user);
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    public User updateUser(@PathVariable Long id, User user){
        return userService.updateUser(id, user);
    }

    @RequestMapping(value = "/{id}/payment-method", method = RequestMethod.POST)
    public User addPaymentMethodToUser(@PathVariable Long id, PaymentMethod paymentMethod){
        User user = userService.findById(id);
        return userService.addPaymentMethodToUser(user, paymentMethod);
    }

    @RequestMapping(value = "/{id}/payment-method", method = RequestMethod.GET)
    public List<PaymentMethod> getPaymentMethodsByUser(@PathVariable Long id){
        User user = userService.findById(id);
        return user.getPaymentMethods();
    }

    @RequestMapping(value = "/{id}/payment-method", method = RequestMethod.DELETE)
    public Boolean deletePaymentMethod(@PathVariable Long id, Long paymentMethodId){
        return userService.removePaymentMethodForUser(id, paymentMethodId);
    }
}