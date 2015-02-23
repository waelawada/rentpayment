package com.waelawada.learn.springboot.dto.users;

import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import com.waelawada.learn.springboot.dto.payments.ResidentPaymentMethodDto;

import java.util.List;

/**
 * Created by waelawada on 2/21/15.
 */
public class FullApartmentResidentDto extends UserDto {
    private List<PaymentMethod> paymentMethods;

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
