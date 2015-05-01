package com.waelawada.learn.springboot.domain.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.waelawada.learn.springboot.domain.Address;
import com.waelawada.learn.springboot.domain.community.Community;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by waelawada on 2/18/15.
 */
@Entity
@DiscriminatorValue("manager")
@Table(name = "manager_user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ManagerUser extends User {

    @ManyToOne
    @JoinColumn(name = "community_id")
    @JsonManagedReference
    private Community community;

    private ManagerUser(Builder builder) {
        setCommunity(builder.community);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public ManagerUser() {
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }


    public static final class Builder extends User.Builder<ManagerUser.Builder> {
        private Community community;

        private Builder() {
        }

        public Builder community(Community community) {
            this.community = community;
            return this;
        }

        public ManagerUser.Builder getThis(){
            return this;
        }

        public ManagerUser build() {
            return new ManagerUser(this);
        }
    }


}
