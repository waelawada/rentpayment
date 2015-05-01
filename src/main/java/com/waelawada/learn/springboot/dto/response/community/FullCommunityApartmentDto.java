package com.waelawada.learn.springboot.dto.response.community;

import com.waelawada.learn.springboot.dto.ApartmentDto;

/**
 * Created by waelawada on 2/21/15.
 */
public class FullCommunityApartmentDto extends ApartmentDto {

    private FullCommunityApartmentResidentDto resident;

    public FullCommunityApartmentResidentDto getResident() {
        return resident;
    }

    public void setResident(FullCommunityApartmentResidentDto resident) {
        this.resident = resident;
    }
}
