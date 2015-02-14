package com.waelawada.learn.springboot.domain.billing;

import javax.persistence.*;

/**
 * Created by waelawada on 2/12/15.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="PAYMENT_METHOD")
@Table(name="payment_method")
public abstract class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
