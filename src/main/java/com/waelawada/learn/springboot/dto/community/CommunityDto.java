package com.waelawada.learn.springboot.dto.community;

import com.waelawada.learn.springboot.domain.Address;
import com.waelawada.learn.springboot.dto.Dto;

/**
 * Created by waelawada on 2/21/15.
 */
public abstract class CommunityDto implements Dto {
    private Long id;
    private String name;
    private Address address;

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

}
