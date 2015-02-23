package com.waelawada.learn.springboot.dto.apartments;

import com.waelawada.learn.springboot.domain.Address;
import com.waelawada.learn.springboot.dto.Dto;

/**
 * Created by waelawada on 2/21/15.
 */
public abstract class ApartmentDto implements Dto {

    private Long id;
    private String apartmentId;
    private double monthlyRent;
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public double getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
