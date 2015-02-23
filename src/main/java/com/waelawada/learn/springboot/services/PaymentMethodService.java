package com.waelawada.learn.springboot.services;

import com.waelawada.learn.springboot.dao.PaymentMethodDao;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import org.springframework.stereotype.Service;

/**
 * Created by waelawada on 2/13/15.
 */
@Service
public class PaymentMethodService extends GenericService<PaymentMethod, PaymentMethodDao> {
}
