package com.waelawada.learn.springboot.services.cardpayment.requests.auth;

import com.waelawada.learn.springboot.services.cardpayment.requests.CardPaymentRequest;

import java.util.Map;

/**
 * Created by waelawada on 2/15/15.
 */
public abstract class GenericAuthRquest implements CardPaymentRequest {

    @Override
    public Map<String, Object> getAsMap() {
        return null;
    }
}
