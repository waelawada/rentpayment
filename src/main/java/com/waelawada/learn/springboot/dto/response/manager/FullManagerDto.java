package com.waelawada.learn.springboot.dto.response.manager;

import com.waelawada.learn.springboot.dto.UserDto;

/**
 * Created by waelawada on 2/21/15.
 */
public class FullManagerDto extends UserDto {

    private FullManagerCommunityDto community;

    public FullManagerCommunityDto getCommunity() {
        return community;
    }

    public void setCommunity(FullManagerCommunityDto community) {
        this.community = community;
    }
}
