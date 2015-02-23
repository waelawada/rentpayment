package com.waelawada.learn.springboot.dto.apartments;

import com.waelawada.learn.springboot.dto.users.FullCommunityApartmentResidentDto;

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
