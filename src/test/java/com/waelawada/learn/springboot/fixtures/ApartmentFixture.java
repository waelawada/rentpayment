package com.waelawada.learn.springboot.fixtures;

import com.waelawada.learn.springboot.domain.Address;
import com.waelawada.learn.springboot.domain.community.Apartment;

/**
 * Created by waelawada on 2/28/15.
 */
public class ApartmentFixture {

    public static final Apartment FULL_APARTMENT_1 = Apartment.newBuilder()
            .address(AddressFixtures.FL_ADDRESS_1).id(1L).apartmentId("1024")
            .monthlyRent(990).residentUser(UserFixtures.FULL_RESIDENT_USER_1)
            .community(CommunityFixtures.FULL_COMMUNITY_1).build();

    public static final Apartment APARTMENT_NORESIDENT_NOCOMMUNITY_1 = Apartment.newBuilder().address(AddressFixtures.FL_ADDRESS_2)
            .id(2L).apartmentId("777").monthlyRent(500).build();

}
