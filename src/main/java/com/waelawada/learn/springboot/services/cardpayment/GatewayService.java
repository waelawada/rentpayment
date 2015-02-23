package com.waelawada.learn.springboot.services.cardpayment;

import com.waelawada.learn.springboot.domain.cardpayment.requests.CardPaymentRequest;
import com.waelawada.learn.springboot.domain.cardpayment.responses.CardPaymentResponse;

/**
 * Created by waelawada on 2/15/15.
 */
public interface GatewayService {
    CardPaymentResponse authCapture(CardPaymentRequest request);

    CardPaymentResponse auth(CardPaymentRequest request);

    CardPaymentResponse capture(CardPaymentRequest request);

    CardPaymentResponse refund(CardPaymentRequest request);

    CardPaymentResponse token(CardPaymentRequest request);
}
