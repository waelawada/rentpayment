package com.waelawada.learn.springboot.dto.response.resident;

import com.waelawada.learn.springboot.dto.ApartmentDto;

/**
 * Created by waelawada on 2/21/15.
 */
public class FullResidentApartmentDto extends ApartmentDto {

    private FullResidentApartmentCommunityDto community;

    public FullResidentApartmentCommunityDto getCommunity() {
        return community;
    }

    public void setCommunity(FullResidentApartmentCommunityDto community) {
        this.community = community;
    }
}
