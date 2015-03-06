package com.waelawada.learn.springboot.domain.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.waelawada.learn.springboot.domain.Address;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import com.waelawada.learn.springboot.domain.community.Apartment;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by waelawada on 2/18/15.
 */
@Entity
@DiscriminatorValue("resident")
@Table(name = "resident_user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ResidentUser extends User {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName="id")
    private List<PaymentMethod> paymentMethods;

    @OneToOne(mappedBy = "residentUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Apartment apartment;

    private ResidentUser(Builder builder) {
        setPaymentMethods(builder.paymentMethods);
        setApartment(builder.apartment);
    }

    public ResidentUser() {
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }


    public static final class Builder extends User.Builder<ResidentUser.Builder> {

        private List<PaymentMethod> paymentMethods;
        private Apartment apartment;

        private Builder() {
        }

        public Builder paymentMethods(List<PaymentMethod> paymentMethods) {
            this.paymentMethods = paymentMethods;
            return this;
        }

        public Builder apartment(Apartment apartment) {
            this.apartment = apartment;
            return this;
        }

        @Override
        public ResidentUser.Builder getThis(){
            return this;
        }

        public ResidentUser build() {
            return new ResidentUser(this);
        }
    }
}
