package com.waelawada.learn.springboot.exception.user;

import com.waelawada.learn.springboot.exception.GenericApplicationException;

/**
 * Created by waelawada on 2/28/15.
 */
public class UserNotFoundException extends GenericApplicationException {

    public UserNotFoundException(Long objectId) {
        super(objectId);
    }

    public UserNotFoundException(String externalObjectId) {
        super(externalObjectId);
    }
}
