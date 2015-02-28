package com.waelawada.learn.springboot.exception;

/**
 * Created by waelawada on 2/28/15.
 */
public class ApplicationError {

    private int code;
    private String description;

    public ApplicationError(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
