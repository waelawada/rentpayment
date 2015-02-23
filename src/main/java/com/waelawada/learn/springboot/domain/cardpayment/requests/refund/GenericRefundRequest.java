package com.waelawada.learn.springboot.domain.cardpayment.requests.refund;

import com.waelawada.learn.springboot.domain.cardpayment.requests.CardPaymentRequest;

import java.util.Map;

/**
 * Created by waelawada on 2/15/15.
 */
public abstract class GenericRefundRequest implements CardPaymentRequest {
    @Override
    public Map<String, Object> getAsMap() {
        return null;
    }
}
