package com.waelawada.learn.springboot.util;

import com.waelawada.learn.springboot.domain.Address;
import org.fluttercode.datafactory.impl.DataFactory;

/**
 * Created by waelawada on 2/14/15.
 */
public class Addresses {

    public static DataFactory dataFactory = new DataFactory();

    public static Address getAddress(){
        Address address = new Address();
        address.setStreetAddress(dataFactory.getAddress());
        address.setCity(dataFactory.getCity());
        address.setZipCode(dataFactory.getNumberText(5));
        address.setState(dataFactory.getRandomChars(2));
        address.setCountry(dataFactory.getRandomChars(2));
        return address;
    }

}
