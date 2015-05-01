package com.waelawada.learn.springboot.exception.user;

import com.waelawada.learn.springboot.exception.GenericApplicationException;

/**
 * Created by waelawada on 3/14/15.
 */
public class ManagerNotFoundException extends GenericApplicationException {

    public ManagerNotFoundException(Long objectId) {
        super(objectId);
    }

    public ManagerNotFoundException(String externalObjectId) {
        super(externalObjectId);
    }
}
