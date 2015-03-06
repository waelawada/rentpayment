package com.waelawada.learn.springboot.domain.community;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.waelawada.learn.springboot.domain.Address;
import com.waelawada.learn.springboot.domain.users.ResidentUser;

import javax.persistence.*;

/**
 * Created by waelawada on 2/21/15.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String apartmentId;


    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "community_id")
    @JsonBackReference
    private Community community;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "resident_id")
    @JsonBackReference
    private ResidentUser residentUser;

    private double monthlyRent;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private Apartment(Builder builder) {
        setId(builder.id);
        setApartmentId(builder.apartmentId);
        setCommunity(builder.community);
        setResidentUser(builder.residentUser);
        setMonthlyRent(builder.monthlyRent);
        setAddress(builder.address);
    }

    public Apartment() {
    }

    public static Builder newBuilder() {
        return new Builder();
    }

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

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public ResidentUser getResidentUser() {
        return residentUser;
    }

    public void setResidentUser(ResidentUser residentUser) {
        this.residentUser = residentUser;
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

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", apartmentId='" + apartmentId + '\'' +
                ", community=" + community +
                ", residentUser=" + residentUser +
                ", monthlyRent=" + monthlyRent +
                ", address=" + address +
                '}';
    }


    public static final class Builder {
        private Long id;
        private String apartmentId;
        private Community community;
        private ResidentUser residentUser;
        private double monthlyRent;
        private Address address;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder apartmentId(String apartmentId) {
            this.apartmentId = apartmentId;
            return this;
        }

        public Builder community(Community community) {
            this.community = community;
            return this;
        }

        public Builder residentUser(ResidentUser residentUser) {
            this.residentUser = residentUser;
            return this;
        }

        public Builder monthlyRent(double monthlyRent) {
            this.monthlyRent = monthlyRent;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Apartment build() {
            return new Apartment(this);
        }
    }
}
