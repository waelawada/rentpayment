package com.waelawada.learn.springboot.dto.payments;

import com.waelawada.learn.springboot.dto.Dto;

/**
 * Created by waelawada on 2/21/15.
 */
public abstract class PaymentMethodDto implements Dto {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
