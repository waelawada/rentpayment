package com.waelawada.learn.springboot.domain.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.waelawada.learn.springboot.domain.Address;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by waelawada on 1/16/15.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="user_type")
@Table(name="user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "join_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;


    public User() {
    }

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                ", joinDate=" + joinDate +
                ", lastUpdated=" + lastUpdated +
                ", lastLogin=" + lastLogin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (joinDate != null ? !joinDate.equals(user.joinDate) : user.joinDate != null) return false;
        if (lastLogin != null ? !lastLogin.equals(user.lastLogin) : user.lastLogin != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (lastUpdated != null ? !lastUpdated.equals(user.lastUpdated) : user.lastUpdated != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (joinDate != null ? joinDate.hashCode() : 0);
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        return result;
    }

    @PrePersist
    private void setJoinAndUpdateDate(){
        this.setJoinDate(new Date());
        this.setLastUpdated(new Date());
    }

    @PreUpdate
    private void setLastUpdateDate(){
        this.setLastUpdated(new Date());
    }

    public static abstract class Builder<T extends Builder<T>>{
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private Address address;
        private Date joinDate;
        private Date lastUpdated;
        private Date lastLogin;

        public Builder(){

        }

        public T id(Long id) {
            this.id = id;
            return getThis();
        }

        public T firstName(String firstName) {
            this.firstName = firstName;
            return getThis();
        }

        public T lastName(String lastName) {
            this.lastName = lastName;
            return getThis();
        }

        public T email(String email) {
            this.email = email;
            return getThis();
        }

        public T password(String password) {
            this.password = password;
            return getThis();
        }

        public T address(Address address) {
            this.address = address;
            return getThis();
        }

        public T joinDate(Date joinDate) {
            this.joinDate = joinDate;
            return getThis();
        }

        public T lastUpdated(Date lastUpdated) {
            this.lastUpdated = lastUpdated;
            return getThis();
        }

        public T lastLogin(Date lastLogin) {
            this.lastLogin = lastLogin;
            return getThis();
        }

        public abstract User build();
        public abstract T getThis();

    }
}
