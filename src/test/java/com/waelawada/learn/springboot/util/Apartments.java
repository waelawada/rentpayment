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
        Apartment apartment = new Apartment();
        apartment.setResidentUser((ResidentUser)Users.getUserWithAddressWithPayments(Users.UserType.RESIDENT));
        apartment.setAddress(Addresses.getAddress());
        apartment.setApartmentId(dataFactory.getRandomText(5,5));
        apartment.setMonthlyRent(dataFactory.getNumberBetween(1000,2000));
        return apartment;
    }


}
