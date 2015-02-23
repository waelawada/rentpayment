package com.waelawada.learn.springboot.services.cardpayment;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.waelawada.learn.springboot.domain.cardpayment.requests.CardPaymentRequest;
import com.waelawada.learn.springboot.domain.cardpayment.responses.CardPaymentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * Created by waelawada on 2/14/15.
 */
@PropertySource("classpath:application.properties")
@Service
public class StripeService implements GatewayService {

    private String apiKey;
    public static String TRANSACTION_ID_CONSTANT = "transactionId";

    public StripeService(){
        Stripe.apiKey = this.getApiKey();
    }

    public String getApiKey() {
        return apiKey;
    }

    @Value("${spring.stripe.api-key}")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public CardPaymentResponse authCapture(CardPaymentRequest request){
        try {
            Charge charge = Charge.create(request.getAsMap());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (CardException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CardPaymentResponse auth(CardPaymentRequest request){
        Object captureValue = request.getAsMap().get("capture");
        if(captureValue != null){
            request.getAsMap().remove("capture");
        }
        request.getAsMap().put("capture", false);
        try {
            Charge charge = Charge.create(request.getAsMap());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (CardException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CardPaymentResponse capture(CardPaymentRequest request){
        try {
            Charge charge = Charge.retrieve(String.valueOf(request.getAsMap().get(TRANSACTION_ID_CONSTANT)));
            charge = charge.capture();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (CardException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CardPaymentResponse refund(CardPaymentRequest request){
        try {
            Charge charge = Charge.retrieve(String.valueOf(request.getAsMap().get(TRANSACTION_ID_CONSTANT)));
            charge = charge.refund();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (CardException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CardPaymentResponse token(CardPaymentRequest request){
        try {
            Token token = Token.create(request.getAsMap());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (CardException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return null;
    }


}
