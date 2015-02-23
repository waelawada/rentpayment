package com.waelawada.learn.springboot.domain.community;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.waelawada.learn.springboot.domain.Address;
import com.waelawada.learn.springboot.domain.users.ManagerUser;
import com.waelawada.learn.springboot.domain.users.User;

import javax.persistence.*;
import java.util.List;

/**
 * Created by waelawada on 2/21/15.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;


    @OneToMany(mappedBy = "community")
    @JsonManagedReference
    private List<Apartment> apartments;


    @OneToMany(mappedBy = "community")
    @JsonBackReference
    private List<ManagerUser> managers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public List<ManagerUser> getManagers() {
        return managers;
    }

    public void setManagers(List<ManagerUser> managers) {
        this.managers = managers;
    }

    public List<Apartment> addApartment(Apartment apartment){
        getApartments().add(apartment);
        return getApartments();
    }

    public List<ManagerUser> addManager(ManagerUser manager){
        getManagers().add(manager);
        return getManagers();
    }
}
