package com.waelawada.learn.springboot.controllers;

import com.waelawada.learn.springboot.converters.UserConverter;
import com.waelawada.learn.springboot.domain.users.ManagerUser;
import com.waelawada.learn.springboot.domain.users.ResidentUser;
import com.waelawada.learn.springboot.domain.users.User;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import com.waelawada.learn.springboot.dto.users.FullManagerDto;
import com.waelawada.learn.springboot.dto.users.FullResidentDto;
import com.waelawada.learn.springboot.dto.users.UserDto;
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
    public UserDto index(@PathVariable Long id) {
        User user = userService.findById(id);
        if(user instanceof ManagerUser){
            return UserConverter.convertEntityToDto(user, FullManagerDto.class);
        }
        else if(user instanceof ResidentUser){
            return UserConverter.convertEntityToDto(user, FullResidentDto.class);
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public User addUser(User user){
        return userService.save(user);
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    public User updateUser(@PathVariable Long id, User user){
        User userToBeUpdated = userService.findById(id);
        return userService.updateUser(userToBeUpdated, user);
    }

    @RequestMapping(value = "/{id}/payment-method", method = RequestMethod.POST)
    public User addPaymentMethodToUser(@PathVariable Long id, PaymentMethod paymentMethod){
        ResidentUser user = (ResidentUser)userService.findById(id);
        return userService.addPaymentMethodToUser(user, paymentMethod);
    }

    @RequestMapping(value = "/{id}/payment-method", method = RequestMethod.GET)
    public List<PaymentMethod> getPaymentMethodsByResidentUser(@PathVariable Long id){
        ResidentUser user = (ResidentUser)userService.findById(id);
        return user.getPaymentMethods();
    }

    @RequestMapping(value = "/{id}/payment-method", method = RequestMethod.DELETE)
    public Boolean deletePaymentMethod(@PathVariable Long id, Long paymentMethodId){
        return userService.removePaymentMethodForUser(id, paymentMethodId);
    }
}