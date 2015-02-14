package com.waelawada.learn.springboot.dao;

import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by waelawada on 2/13/15.
 */
public interface PaymentMethodDao extends CrudRepository<PaymentMethod, Long> {
}
