package com.waelawada.learn.springboot.dto.users;

import com.waelawada.learn.springboot.dto.community.FullManagerCommunityDto;

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
