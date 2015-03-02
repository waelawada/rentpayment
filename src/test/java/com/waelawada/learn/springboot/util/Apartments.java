package com.waelawada.learn.springboot.util;

import com.waelawada.learn.springboot.domain.Address;
import com.waelawada.learn.springboot.domain.community.Apartment;
import com.waelawada.learn.springboot.domain.users.ResidentUser;
import org.fluttercode.datafactory.impl.DataFactory;

/**
 * Created by waelawada on 2/21/15.
 */
public class Apartments {
    private static DataFactory dataFactory = new DataFactory();

    public static Apartment getApartment(){
        Apartment apartment = Apartment.newBuilder()
        .residentUser((ResidentUser)Users.getUserWithAddressWithPayments(Users.UserType.RESIDENT))
        .address(Addresses.getAddress())
        .apartmentId(dataFactory.getRandomText(5,5))
        .monthlyRent(dataFactory.getNumberBetween(1000,2000)).build();
        return apartment;
    }


}
