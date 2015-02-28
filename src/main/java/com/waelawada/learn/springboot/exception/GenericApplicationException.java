package com.waelawada.learn.springboot.exception;

/**
 * Created by waelawada on 2/28/15.
 */
public class GenericApplicationException extends RuntimeException {

    private Long objectId;
    private String externalObjectId;

    public GenericApplicationException() {
    }

    public GenericApplicationException(Long objectId){
        this.objectId = objectId;
    }

    public GenericApplicationException(String externalObjectId){
        this.externalObjectId = externalObjectId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getExternalObjectId() {
        return externalObjectId;
    }

    public void setExternalObjectId(String externalObjectId) {
        this.externalObjectId = externalObjectId;
    }
}
