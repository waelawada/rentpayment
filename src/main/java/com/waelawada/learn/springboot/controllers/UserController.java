package com.waelawada.learn.springboot.controllers;

import com.waelawada.learn.springboot.converters.UserConverter;
import com.waelawada.learn.springboot.domain.users.ManagerUser;
import com.waelawada.learn.springboot.domain.users.ResidentUser;
import com.waelawada.learn.springboot.domain.users.User;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import com.waelawada.learn.springboot.dto.users.FullManagerDto;
import com.waelawada.learn.springboot.dto.users.FullResidentDto;
import com.waelawada.learn.springboot.dto.users.UserDto;
import com.waelawada.learn.springboot.exception.ApplicationError;
import com.waelawada.learn.springboot.exception.apartment.ApartmentNotFoundException;
import com.waelawada.learn.springboot.exception.community.CommunityNotFoundException;
import com.waelawada.learn.springboot.exception.user.UserNotFoundException;
import com.waelawada.learn.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}")
    public UserDto index(@PathVariable Long id) {
        User user = userService.findById(id);
        if(user == null) throw new UserNotFoundException(id);
        return getUserDto(user);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserDto addUser(User user){
        return getUserDto(userService.save(user));
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    public UserDto updateUser(@PathVariable Long id, User user){
        User userToBeUpdated = userService.findById(id);
        User updatedUser = userService.updateUser(userToBeUpdated, user);
        return getUserDto(updatedUser);
    }

    private UserDto getUserDto(User updatedUser) {
        if(updatedUser instanceof ManagerUser){
            return UserConverter.convertEntityToDto(updatedUser, FullManagerDto.class);
        }
        else{
            return UserConverter.convertEntityToDto(updatedUser, FullResidentDto.class);
        }
    }

    @RequestMapping(value = "/{id}/payment-method", method = RequestMethod.POST)
    public UserDto addPaymentMethodToUser(@PathVariable Long id, PaymentMethod paymentMethod){
        ResidentUser user = (ResidentUser)userService.findById(id);
        return getUserDto(userService.addPaymentMethodToUser(user, paymentMethod));
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

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApplicationError userNotFound(UserNotFoundException upe){
        Long userId = upe.getObjectId();
        String externalUserId = upe.getExternalObjectId();
        String messageId = (externalUserId!=null ? externalUserId : String.valueOf(userId));
        return new ApplicationError(31, "User "+messageId+" not found");
    }
}