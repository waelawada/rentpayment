package com.waelawada.learn.springboot.dto.community;

import com.waelawada.learn.springboot.dto.users.FullResidentApartmentCommunityManagerDto;

import java.util.List;

/**
 * Created by waelawada on 2/21/15.
 */
public class FullResidentApartmentCommunityDto extends CommunityDto {

    private List<FullResidentApartmentCommunityManagerDto> managers;

    public List<FullResidentApartmentCommunityManagerDto> getManagers() {
        return managers;
    }

    public void setManagers(List<FullResidentApartmentCommunityManagerDto> managers) {
        this.managers = managers;
    }
}
