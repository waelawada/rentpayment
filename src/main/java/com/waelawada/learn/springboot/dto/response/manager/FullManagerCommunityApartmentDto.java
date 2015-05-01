package com.waelawada.learn.springboot.dto.response.manager;

import com.waelawada.learn.springboot.dto.ApartmentDto;

/**
 * Created by waelawada on 2/21/15.
 */
public class FullManagerCommunityApartmentDto extends ApartmentDto {

    private FullManagerCommunityApartmentResidentDto resident;

    public FullManagerCommunityApartmentResidentDto getResident() {
        return resident;
    }

    public void setResident(FullManagerCommunityApartmentResidentDto resident) {
        this.resident = resident;
    }
}
