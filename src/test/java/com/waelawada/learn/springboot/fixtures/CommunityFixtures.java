package com.waelawada.learn.springboot.fixtures;

import com.waelawada.learn.springboot.domain.community.Community;

import java.util.Arrays;

/**
 * Created by waelawada on 2/28/15.
 */
public class CommunityFixtures {

    public static final Community FULL_COMMUNITY_1 = Community.newBuilder().address(AddressFixtures.FL_ADDRESS_1)
            .apartments(Arrays.asList(ApartmentFixture.APARTMENT_1))
            .id(1L).name("Bella Vista").managers(Arrays.asList(UserFixtures.FULL_MANAGER_USER_1)).build();

}
