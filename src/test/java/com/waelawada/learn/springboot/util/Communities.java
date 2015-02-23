package com.waelawada.learn.springboot.util;

import com.waelawada.learn.springboot.domain.community.Community;
import com.waelawada.learn.springboot.domain.users.ManagerUser;
import org.fluttercode.datafactory.impl.DataFactory;

import java.util.Arrays;

/**
 * Created by waelawada on 2/21/15.
 */
public class Communities {


    private static DataFactory dataFactory = new DataFactory();

    public static Community getEmptyCommunity(){
        Community community = new Community();
        community.setName(dataFactory.getRandomWord());
        return community;
    }

    public static Community getCommunityWithManagers(int numberOfManagers){
        Community community = getEmptyCommunity();
        return addManagersToCommunity(community, numberOfManagers);
    }

    public static Community getCommunityWithApartments(int numberOfApartments){
        Community community = getEmptyCommunity();
        return addManagersToCommunity(community, numberOfApartments);
    }

    public static Community getCommunityWithApartmentsAndManagers(int numberOfApartments, int numberOfManagers){
        Community community = getEmptyCommunity();
        community = addApartmentsToCommunity(community, numberOfApartments);
        community = addManagersToCommunity(community, numberOfManagers);
        return community;
    }

    public static Community addApartmentsToCommunity(Community community, int numberOfApartments) {
        for (int i = 0; i < numberOfApartments; i++) {
            community.addApartment(Apartments.getApartment());
        }
        return community;
    }

    public static Community addManagersToCommunity(Community community, int numberOfManagers) {
        for (int i = 0; i < numberOfManagers; i++) {
            community.addManager((ManagerUser) Users.getUserWithAddressWithoutPayments(Users.UserType.MANAGER));
        }
        return community;
    }

}
