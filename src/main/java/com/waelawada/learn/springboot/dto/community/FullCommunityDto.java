package com.waelawada.learn.springboot.dto.community;

import com.waelawada.learn.springboot.dto.apartments.FullCommunityApartmentDto;
import com.waelawada.learn.springboot.dto.users.FullCommunityManagerDto;

import java.util.List;

/**
 * Created by waelawada on 2/21/15.
 */
public class FullCommunityDto extends CommunityDto {

    private List<FullCommunityManagerDto> managers;
    private List<FullCommunityApartmentDto> apartments;

    public List<FullCommunityManagerDto> getManagers() {
        return managers;
    }

    public void setManagers(List<FullCommunityManagerDto> managers) {
        this.managers = managers;
    }

    public List<FullCommunityApartmentDto> getApartments() {
        return apartments;
    }

    public void setApartments(List<FullCommunityApartmentDto> apartments) {
        this.apartments = apartments;
    }
}
