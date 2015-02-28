package com.waelawada.learn.springboot.exception.apartment;

import com.waelawada.learn.springboot.exception.GenericApplicationException;

/**
 * Created by waelawada on 2/28/15.
 */
public class ApartmentNotFoundException extends GenericApplicationException{
    public ApartmentNotFoundException(Long objectId) {
        super(objectId);
    }

    public ApartmentNotFoundException(String externalObjectId) {
        super(externalObjectId);
    }
}
