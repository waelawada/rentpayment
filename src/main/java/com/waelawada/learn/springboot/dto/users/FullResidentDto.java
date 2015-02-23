package com.waelawada.learn.springboot.dto.users;

import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import com.waelawada.learn.springboot.dto.apartments.FullResidentApartmentDto;

import java.util.List;

/**
 * Created by waelawada on 2/21/15.
 */
public class FullResidentDto extends UserDto {

    private FullResidentApartmentDto apartment;

    private List<PaymentMethod> paymentMethods;


    public FullResidentApartmentDto getApartment() {
        return apartment;
    }

    public void setApartment(FullResidentApartmentDto apartment) {
        this.apartment = apartment;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
