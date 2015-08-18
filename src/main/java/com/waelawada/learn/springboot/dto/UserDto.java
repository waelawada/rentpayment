package com.waelawada.learn.springboot.dto;

import com.waelawada.learn.springboot.domain.Address;
import com.waelawada.learn.springboot.dto.Dto;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.Date;

/**
 * Created by waelawada on 2/21/15.
 */
@ApiObject
public abstract class UserDto implements Dto {

    @ApiObjectField
    private Long id;
    @ApiObjectField
    private String firstName;
    @ApiObjectField
    private String lastName;
    @ApiObjectField
    private String email;
    @ApiObjectField
    private String password;
    @ApiObjectField
    private Address address;
    @ApiObjectField
    private Date joinDate;
    @ApiObjectField
    private Date lastUpdated;
    @ApiObjectField
    private Date lastLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
