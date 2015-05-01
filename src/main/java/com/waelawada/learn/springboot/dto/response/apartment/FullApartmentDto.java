package com.waelawada.learn.springboot.dto.response.apartment;

import com.waelawada.learn.springboot.dto.ApartmentDto;

/**
 * Created by waelawada on 2/21/15.
 */
public class FullApartmentDto extends ApartmentDto {

    private FullApartmentCommunityDto community;
    private FullApartmentResidentDto resident;

    public FullApartmentCommunityDto getCommunity() {
        return community;
    }

    public void setCommunity(FullApartmentCommunityDto community) {
        this.community = community;
    }

    public FullApartmentResidentDto getResident() {
        return resident;
    }

    public void setResident(FullApartmentResidentDto resident) {
        this.resident = resident;
    }
}
