package com.waelawada.learn.springboot.fixtures;

import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import com.waelawada.learn.springboot.domain.users.ManagerUser;
import com.waelawada.learn.springboot.domain.users.ResidentUser;

import java.util.Arrays;
import java.util.List;

/**
 * Created by waelawada on 2/28/15.
 */
public class UserFixtures {

    public static final ResidentUser FULL_RESIDENT_USER_1 = ResidentUser.newBuilder()
            .paymentMethods((List<PaymentMethod>) (List<?>) Arrays.asList(PaymentMethodFixture.CREDIT_CARD_1))
            .apartment(ApartmentFixture.FULL_APARTMENT_1)
            .firstName("John").lastName("Doe").email("john@doe.com").address(AddressFixtures.FL_ADDRESS_1)
            .password("password").id(1L).build();

    public static final ManagerUser FULL_MANAGER_USER_1 = ManagerUser.newBuilder()
            .firstName("Jane").lastName("Doe").address(AddressFixtures.FL_ADDRESS_2)
            .email("jane@doe.com").password("password2")
            .id(2L).community(CommunityFixtures.FULL_COMMUNITY_1).build();


}
