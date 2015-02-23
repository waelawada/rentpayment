package com.waelawada.learn.springboot.converters;

import com.waelawada.learn.springboot.domain.users.ManagerUser;
import com.waelawada.learn.springboot.domain.users.ResidentUser;
import com.waelawada.learn.springboot.domain.users.User;
import com.waelawada.learn.springboot.dto.community.FullManagerCommunityDto;
import com.waelawada.learn.springboot.dto.users.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by waelawada on 2/22/15.
 */
public class UserConverter {

    public static User convertDtoToEntity(UserDto userDto){
        User user = null;
        if(userDto == null) return null;
        if(userDto instanceof FullManagerDto){
            user = new ManagerUser();
            ((ManagerUser)user).setCommunity(CommunityConverter.convertDtoToEntity(((FullManagerDto) userDto).getCommunity()));
        }
        else if(userDto instanceof FullApartmentCommunityManagerDto || userDto instanceof FullResidentApartmentCommunityManagerDto){
            user = new ManagerUser();
        }
        else if(userDto instanceof FullApartmentResidentDto){
            user = new ResidentUser();
            ((ResidentUser) user).setPaymentMethods(((FullApartmentResidentDto)userDto).getPaymentMethods());
        }
        else if(userDto instanceof  FullCommunityApartmentResidentDto){
            user = new ResidentUser();
            ((ResidentUser) user).setPaymentMethods(((FullCommunityApartmentResidentDto)userDto).getPaymentMethods());
        }
        else if(userDto instanceof FullManagerCommunityApartmentResidentDto){
            user = new ResidentUser();
            ((ResidentUser) user).setPaymentMethods(((FullManagerCommunityApartmentResidentDto)userDto).getPaymentMethods());
        }
        else if(userDto instanceof FullResidentDto){
            user = new ResidentUser();
            ((ResidentUser) user).setPaymentMethods(((FullResidentDto)userDto).getPaymentMethods());
        }

        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setJoinDate(userDto.getJoinDate());
        user.setLastLogin(userDto.getLastLogin());
        user.setLastUpdated(userDto.getLastUpdated());

        return user;
    }

    public static <T extends UserDto> T convertEntityToDto(User user, Class<T> targetClass){
        UserDto userDto=null;
        if(user == null) return null;
        try {
            if(targetClass == getClassByClassName("FullApartmentCommunityManagerDto")){
                userDto = new FullApartmentCommunityManagerDto();
            }
            else if(targetClass == getClassByClassName("FullCommunityManagerDto")){
                userDto = new FullCommunityManagerDto();
            }
            else if(targetClass == getClassByClassName("FullManagerDto")){
                userDto = new FullManagerDto();
                ((FullManagerDto)userDto).setCommunity((FullManagerCommunityDto)CommunityConverter.convertEntityToDto(((ManagerUser) user).getCommunity(), FullManagerCommunityDto.class));
            }
            else if(targetClass == getClassByClassName("FullResidentApartmentCommunityManagerDto")){
                userDto = new FullResidentApartmentCommunityManagerDto();
            }
            else if(targetClass == getClassByClassName("FullApartmentResidentDto")){
                userDto = new FullApartmentResidentDto();
                ((FullApartmentResidentDto) userDto).setPaymentMethods(((ResidentUser)user).getPaymentMethods());
            }
            else if(targetClass == getClassByClassName("FullCommunityApartmentResidentDto")){
                userDto = new FullCommunityApartmentResidentDto();
                ((FullCommunityApartmentResidentDto)userDto).setPaymentMethods(((ResidentUser)user).getPaymentMethods());
            }
            else if(targetClass == getClassByClassName("FullManagerCommunityApartmentResidentDto")){
                userDto = new FullManagerCommunityApartmentResidentDto();
                ((FullManagerCommunityApartmentResidentDto)userDto).setPaymentMethods(((ResidentUser)user).getPaymentMethods());
            }
            else if(targetClass == getClassByClassName("FullResidentDto")){
                userDto = new FullResidentDto();
                ((FullResidentDto)userDto).setPaymentMethods(((ResidentUser)user).getPaymentMethods());
            }

            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setAddress(user.getAddress());
            userDto.setLastUpdated(user.getLastUpdated());
            userDto.setLastLogin(user.getLastLogin());
            userDto.setJoinDate(user.getJoinDate());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (targetClass.cast(userDto));
    }

    private static Class getClassByClassName(String className) throws ClassNotFoundException {
        return Class.forName("com.waelawada.learn.springboot.dto.users."+className);
    }

    public static <T extends UserDto, U extends User> List<U> convertDtoListToEntityList(List<T> userDtos){
        List<U> users = new LinkedList<U>();
        for (T userDto : userDtos) {
            users.add((U)convertDtoToEntity(userDto));
        }
        return users;
    }

    public static <T extends User, U extends UserDto> List<U> convertEntityListToDtoList(List<T> users, Class<U> targetClass){
        List<U> userDtos = new LinkedList<U>();
        for (User user : users) {
            userDtos.add((U)convertEntityToDto(user, targetClass));
        }
        return userDtos;
    }

}
