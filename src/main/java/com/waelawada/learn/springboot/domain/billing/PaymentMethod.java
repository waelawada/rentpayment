package com.waelawada.learn.springboot.domain.billing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;

/**
 * Created by waelawada on 2/12/15.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="PAYMENT_METHOD")
@Table(name="payment_method")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiObjectField
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
