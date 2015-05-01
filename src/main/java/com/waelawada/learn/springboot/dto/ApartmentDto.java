package com.waelawada.learn.springboot.dto;

import com.waelawada.learn.springboot.domain.Address;
import com.waelawada.learn.springboot.dto.Dto;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * Created by waelawada on 2/21/15.
 */
public abstract class ApartmentDto implements Dto {

    @ApiObjectField(name = "id", description = "Database Id of Apartment")
    private Long id;
    @ApiObjectField(name = "apartmentId", description = "External Id Of Apartment")
    private String apartmentId;
    @ApiObjectField(name = "monthlyRent", description = "The monthly Rent Of an Apartment")
    private double monthlyRent;
    @ApiObjectField(name = "address", description = "The Address of the Apartment")
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
