package com.waelawada.learn.springboot.exception.community;

import com.waelawada.learn.springboot.exception.GenericApplicationException;

/**
 * Created by waelawada on 2/28/15.
 */
public class CommunityNotFoundException extends GenericApplicationException {

    public CommunityNotFoundException(String externalObjectId) {
        super(externalObjectId);
    }

    public CommunityNotFoundException(Long objectId) {
        super(objectId);
    }
}
