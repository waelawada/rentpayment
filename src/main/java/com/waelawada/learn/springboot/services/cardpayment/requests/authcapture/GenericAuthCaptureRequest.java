package com.waelawada.learn.springboot.services.cardpayment.requests.authcapture;

import com.waelawada.learn.springboot.services.cardpayment.requests.CardPaymentRequest;

import java.util.Map;

/**
 * Created by waelawada on 2/15/15.
 */
public abstract class GenericAuthCaptureRequest implements CardPaymentRequest {
    @Override
    public Map<String, Object> getAsMap() {
        return null;
    }
}
