package com.waelawada.learn.springboot.controllers;

import com.waelawada.learn.springboot.exception.ApplicationError;
import com.waelawada.learn.springboot.exception.apartment.ApartmentNotFoundException;
import com.waelawada.learn.springboot.exception.community.CommunityNotFoundException;
import com.waelawada.learn.springboot.exception.user.ManagerNotFoundException;
import com.waelawada.learn.springboot.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by waelawada on 3/14/15.
 */
public abstract class GenericController {

    @ExceptionHandler(ManagerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApplicationError managerNotFoundException(ManagerNotFoundException mnfe){
        Long managerId = mnfe.getObjectId();
        String externalManagerId = mnfe.getExternalObjectId();
        String messageId = (externalManagerId!=null ? externalManagerId : String.valueOf(managerId));
        return new ApplicationError(11, "Manager "+messageId+" not found");
    }

    @ExceptionHandler(value = CommunityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApplicationError communityNotFound(CommunityNotFoundException cpe){
        Long communityId = cpe.getObjectId();
        String externalCommunityId= cpe.getExternalObjectId();
        String messageId = (externalCommunityId!=null ? externalCommunityId : String.valueOf(communityId));
        return new ApplicationError(21, "Community "+messageId+" not found");
    }

    @ExceptionHandler(value = ApartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApplicationError apartmentNotFound(ApartmentNotFoundException ape){
        Long apartmentId = ape.getObjectId();
        String externalApartmentId = ape.getExternalObjectId();
        String messageId = (externalApartmentId!=null ? externalApartmentId : String.valueOf(apartmentId));
        return new ApplicationError(11, "Apartment "+messageId+" not found");
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
