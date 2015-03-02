package com.waelawada.learn.springboot.fixtures;

import com.waelawada.learn.springboot.domain.community.Apartment;

/**
 * Created by waelawada on 2/28/15.
 */
public class ApartmentFixture {

    public static final Apartment APARTMENT_1 = Apartment.newBuilder()
            .address(AddressFixtures.FL_ADDRESS_1).id(1L).apartmentId("1024")
            .monthlyRent(990).residentUser(UserFixtures.FULL_RESIDENT_USER_1)
            .community(CommunityFixtures.FULL_COMMUNITY_1).build();
}
