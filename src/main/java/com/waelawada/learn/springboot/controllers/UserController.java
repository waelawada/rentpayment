package com.waelawada.learn.springboot.controllers;

import com.waelawada.learn.springboot.converters.UserConverter;
import com.waelawada.learn.springboot.dao.PaymentMethodDao;
import com.waelawada.learn.springboot.domain.users.ManagerUser;
import com.waelawada.learn.springboot.domain.users.ResidentUser;
import com.waelawada.learn.springboot.domain.users.User;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import com.waelawada.learn.springboot.dto.request.manager.RequestManagerDto;
import com.waelawada.learn.springboot.dto.request.resident.RequestResidentDto;
import com.waelawada.learn.springboot.dto.response.manager.FullManagerDto;
import com.waelawada.learn.springboot.dto.response.resident.FullResidentDto;
import com.waelawada.learn.springboot.dto.UserDto;
import com.waelawada.learn.springboot.exception.ApplicationError;
import com.waelawada.learn.springboot.exception.user.UserNotFoundException;
import com.waelawada.learn.springboot.jsondoc.JsonDocConstants;
import com.waelawada.learn.springboot.services.PaymentMethodService;
import com.waelawada.learn.springboot.services.UserService;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiVerb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(name = "User", description = "Access the user service")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentMethodService paymentMethodService;


    @ApiMethod(id= JsonDocConstants.USER_GET, description = "Get information about a user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, verb = ApiVerb.GET)
    @RequestMapping(value = "/{id}")
    public @ApiResponseObject UserDto index(@ApiPathParam(name = "id", description = "The Id of the User") @PathVariable Long id) {
        User user = userService.findById(id);
        if(user == null) throw new UserNotFoundException(id);
        return getUserDto(user);
    }

    @ApiMethod(id= JsonDocConstants.USER_ADD, description = "Add a new User", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, verb = ApiVerb.POST)
    @RequestMapping(method = RequestMethod.POST)
    public @ApiResponseObject UserDto addUser(@ApiBodyObject(clazz = UserDto.class) @RequestBody UserDto userDto){
        return getUserDto(userService.save(UserConverter.convertDtoToEntity(userDto)));
    }

    @ApiMethod(id = JsonDocConstants.USER_UPDATE, description = "Update a User", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, verb = ApiVerb.PUT)
    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    public @ApiResponseObject UserDto updateUser(
            @ApiPathParam(name = "id", description = "The Id of the User") @PathVariable Long id,
            @ApiBodyObject(clazz = UserDto.class) @RequestBody UserDto user){
        User userToBeUpdated = userService.findById(id);
        try{
            user = (RequestManagerDto) user;
        }
        catch (ClassCastException cce){
            user = (RequestResidentDto) user;
        }
        User updatedUser = userService.updateUser(userToBeUpdated, UserConverter.convertDtoToEntity(user));
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

    @ApiMethod(id = JsonDocConstants.USER_PAYMENT_ADD, description = "Add a payment Method to a User", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, verb = ApiVerb.POST)
    @RequestMapping(value = "/{id}/payment-method", method = RequestMethod.POST)
    public @ApiResponseObject UserDto addPaymentMethodToUser(
            @ApiPathParam(description = "The Id of the User", name = "id") @PathVariable Long id,
            @ApiBodyObject(clazz = PaymentMethod.class) @RequestBody PaymentMethod paymentMethod){
        ResidentUser user = (ResidentUser)userService.findById(id);
        if(user == null) throw new UserNotFoundException(id);
        return getUserDto(userService.addPaymentMethodToUser(user, paymentMethod));
    }

    @ApiMethod(id = JsonDocConstants.USER_PAYMENT_LIST, description = "Get a list of payment methods belonging to a user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, verb = ApiVerb.GET)
    @RequestMapping(value = "/{id}/payment-method", method = RequestMethod.GET)
    public @ApiResponseObject List<PaymentMethod> getPaymentMethodsByResidentUser(
            @ApiPathParam(name = "id", description = "The Id of the user") @PathVariable Long id){
        ResidentUser user = (ResidentUser)userService.findById(id);
        if(user == null) throw new UserNotFoundException(id);
        return user.getPaymentMethods();
    }

    @ApiMethod(id = JsonDocConstants.USER_PAYMENT_REMOVE, description = "Remove a Payment Method for a User", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, verb = ApiVerb.DELETE)
    @RequestMapping(value = "/{id}/payment-method", method = RequestMethod.DELETE)
    public @ApiResponseObject UserDto deletePaymentMethod(
            @ApiPathParam(name = "id", description = "The Id of the User") @PathVariable Long id,
            @ApiQueryParam(name = "paymentMethodId", description = "The Id of the payment method to be removed") Long paymentMethodId){
        ResidentUser user = (ResidentUser)userService.findById(id);
        if(user == null) throw new UserNotFoundException(id);
        user.getPaymentMethods().remove(paymentMethodService.findById(id));
        return UserConverter.convertEntityToDto(userService.save(user), FullResidentDto.class);
    }


}