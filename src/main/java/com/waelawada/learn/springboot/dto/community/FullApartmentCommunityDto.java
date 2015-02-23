package com.waelawada.learn.springboot.dto.community;

import com.waelawada.learn.springboot.dto.users.FullApartmentCommunityManagerDto;

import java.util.List;

/**
 * Created by waelawada on 2/21/15.
 */
public class FullApartmentCommunityDto extends CommunityDto {

    private List<FullApartmentCommunityManagerDto> managers;

    public List<FullApartmentCommunityManagerDto> getManagers() {
        return managers;
    }

    public void setManagers(List<FullApartmentCommunityManagerDto> managers) {
        this.managers = managers;
    }
}
