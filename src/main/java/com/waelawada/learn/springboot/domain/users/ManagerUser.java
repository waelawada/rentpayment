package com.waelawada.learn.springboot.domain.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.waelawada.learn.springboot.domain.community.Community;

import javax.persistence.*;

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

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}
