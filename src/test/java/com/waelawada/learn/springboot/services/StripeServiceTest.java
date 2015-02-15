package com.waelawada.learn.springboot.services;

import com.waelawada.learn.springboot.Application;
import com.waelawada.learn.springboot.services.cardpayment.StripeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by waelawada on 2/14/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class StripeServiceTest {

    @Autowired
    private StripeService stripeService;

    @Test
    public void testStripeServiceInitialization(){

        System.out.println(stripeService.getApiKey());

    }
}
