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
        if(userDto == null) return null;
        if(userDto instanceof FullManagerDto
                ||userDto instanceof FullApartmentCommunityManagerDto
                || userDto instanceof FullResidentApartmentCommunityManagerDto){
            return setVariablesForManagerDto(userDto);
        }
        else return setVariablesForResidentDto(userDto);
    }

    private static void setVariablesInUserDto(UserDto userDto, User.Builder userBuilder) {
        userBuilder.address(userDto.getAddress());
        userBuilder.email(userDto.getEmail());
        userBuilder.firstName(userDto.getFirstName());
        userBuilder.lastName(userDto.getLastName());
        userBuilder.password(userDto.getPassword());
        userBuilder.joinDate(userDto.getJoinDate());
        userBuilder.lastLogin(userDto.getLastLogin());
        userBuilder.lastUpdated(userDto.getLastUpdated());
    }

    private static ManagerUser setVariablesForManagerDto(UserDto userDto){
        ManagerUser.Builder userBuilder = ManagerUser.newBuilder();
        if(userDto instanceof FullManagerDto) {
            (userBuilder).community(CommunityConverter.convertDtoToEntity(((FullManagerDto) userDto).getCommunity()));
        }
        setVariablesInUserDto(userDto, userBuilder);
        return userBuilder.build();
    }

    private static ResidentUser setVariablesForResidentDto(UserDto userDto){
        ResidentUser.Builder userBuilder = ResidentUser.newBuilder();
        (userBuilder).paymentMethods(((FullApartmentResidentDto) userDto).getPaymentMethods());
        setVariablesInUserDto(userDto, userBuilder);
        return userBuilder.build();
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

    public static List<UserDto> convertEntityListToDtoList(List<? extends User> users, Class<? extends UserDto> targetClass){
        List<UserDto> userDtos = new LinkedList<UserDto>();
        for (User user : users) {
            userDtos.add(convertEntityToDto(user, targetClass));
        }
        return userDtos;
    }

}
