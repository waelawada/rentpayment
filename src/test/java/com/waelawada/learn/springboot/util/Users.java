package com.waelawada.learn.springboot.util;

import com.waelawada.learn.springboot.domain.Address;
import com.waelawada.learn.springboot.domain.users.ManagerUser;
import com.waelawada.learn.springboot.domain.users.ResidentUser;
import com.waelawada.learn.springboot.domain.users.User;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import org.fluttercode.datafactory.impl.DataFactory;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by waelawada on 2/14/15.
 */
public class Users {

    public static DataFactory dataFactory = new DataFactory();

    public enum UserType {
        RESIDENT, MANAGER, RANDOM
    }

    public static User getUserWithoutAddressWithoutPayments(UserType userType){
        User user = getUserByType(userType);
        user.setFirstName(dataFactory.getFirstName());
        user.setLastName(dataFactory.getLastName());
        user.setPassword(dataFactory.getRandomChars(10));
        user.setEmail(dataFactory.getEmailAddress());
        return user;
    }

    public static User getUserWithAddressWithoutPayments(UserType userType){
        User user = getUserWithoutAddressWithoutPayments(userType);
        Address address = Addresses.getAddress();
        user.setAddress(address);
        return user;
    }

    public static User getUserWithAddressWithPayments(UserType userType){
        User user = getUserWithAddressWithoutPayments(userType);
        PaymentMethod paymentMethod = PaymentMethods.getRandomPaymentMethod();
        if(UserType.RESIDENT == userType) {
            ((ResidentUser)user).setPaymentMethods(new LinkedList<PaymentMethod>(Arrays.asList(paymentMethod)));
        }
        return user;
    }

    private static User getUserByType(UserType usertype){
        if(UserType.RESIDENT == usertype){
            return new ResidentUser();
        }
        else if(UserType.MANAGER == usertype){
            return new ManagerUser();
        }
        else{
            return getRandomUser();
        }
    }

    private static User getRandomUser(){
        double random = Math.random();
        if(random < 0.5){
            return new ResidentUser();
        }
        else{
            return new ManagerUser();
        }
    }

}
