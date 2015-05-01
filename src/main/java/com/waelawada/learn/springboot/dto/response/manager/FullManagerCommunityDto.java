package com.waelawada.learn.springboot.dto.response.manager;

import com.waelawada.learn.springboot.dto.CommunityDto;

import java.util.List;

/**
 * Created by waelawada on 2/21/15.
 */
public class FullManagerCommunityDto extends CommunityDto {

    private List<FullManagerCommunityApartmentDto> apartments;

    public List<FullManagerCommunityApartmentDto> getApartments() {
        return apartments;
    }

    public void setApartments(List<FullManagerCommunityApartmentDto> apartments) {
        this.apartments = apartments;
    }
}
