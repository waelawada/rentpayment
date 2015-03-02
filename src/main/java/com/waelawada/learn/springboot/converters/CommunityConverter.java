package com.waelawada.learn.springboot.converters;

import com.waelawada.learn.springboot.domain.community.Community;
import com.waelawada.learn.springboot.domain.users.ManagerUser;
import com.waelawada.learn.springboot.dto.apartments.FullCommunityApartmentDto;
import com.waelawada.learn.springboot.dto.apartments.FullManagerCommunityApartmentDto;
import com.waelawada.learn.springboot.dto.community.*;
import com.waelawada.learn.springboot.dto.users.FullApartmentCommunityManagerDto;
import com.waelawada.learn.springboot.dto.users.FullCommunityManagerDto;
import com.waelawada.learn.springboot.dto.users.FullResidentApartmentCommunityManagerDto;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by waelawada on 2/21/15.
 */
public class CommunityConverter {

    public static Community convertDtoToEntity(CommunityDto communityDto){
        if(communityDto == null) return null;
        Community.Builder communityBuilder = Community.newBuilder();
        if(communityDto instanceof FullApartmentCommunityDto){
            communityBuilder.managers((List<ManagerUser>) (List<?>) UserConverter.convertDtoListToEntityList(((FullApartmentCommunityDto) communityDto).getManagers()));
        }
        else if(communityDto instanceof FullCommunityDto){
            communityBuilder.managers((List<ManagerUser>) (List<?>) UserConverter.convertDtoListToEntityList(((FullCommunityDto) communityDto).getManagers()));

            communityBuilder.apartments(ApartmentConverter.convertDtoListToEntityList(((FullCommunityDto) communityDto).getApartments()));
        }
        else if(communityDto instanceof FullManagerCommunityDto){
            communityBuilder.apartments(ApartmentConverter.convertDtoListToEntityList(((FullManagerCommunityDto) communityDto).getApartments()));
        }
        else if(communityDto instanceof FullResidentApartmentCommunityDto){
            communityBuilder.managers((List<ManagerUser>) (List<?>) UserConverter.convertDtoListToEntityList(((FullResidentApartmentCommunityDto) communityDto).getManagers()));
        }
        communityBuilder.id(communityDto.getId());
        communityBuilder.name(communityDto.getName());
        communityBuilder.address(communityDto.getAddress());
        return communityBuilder.build();
    }

    public static CommunityDto convertEntityToDto(Community community, Class communityDtoClass){
        CommunityDto communityDto = null;
        if(community == null) return null;
        try {
            if(communityDtoClass == getClassByClassName("FullApartmentCommunityDto")){
                communityDto = new FullApartmentCommunityDto();
                ((FullApartmentCommunityDto) communityDto).setManagers(UserConverter.convertEntityListToDtoList(community.getManagers(), FullApartmentCommunityManagerDto.class));
            }
            else if(communityDtoClass == getClassByClassName("FullCommunityDto")){
                communityDto = new FullCommunityDto();
                ((FullCommunityDto) communityDto).setApartments(
                        (List<FullCommunityApartmentDto>)(List<?>)
                                ApartmentConverter.convertEntityListToDtoList(
                                        community.getApartments(), FullCommunityApartmentDto.class));
                ((FullCommunityDto) communityDto).setManagers(
                        (List<FullCommunityManagerDto>)(List<?>)
                                UserConverter.convertEntityListToDtoList(community.getManagers(), FullCommunityManagerDto.class));
            }
            else if(communityDtoClass == getClassByClassName("FullManagerCommunityDto")){
                communityDto = new FullManagerCommunityDto();
                ((FullManagerCommunityDto) communityDto).setApartments(
                        (List<FullManagerCommunityApartmentDto>)(List<?>)
                                ApartmentConverter.convertEntityListToDtoList(community.getApartments(), FullManagerCommunityApartmentDto.class));
            }
            else if(communityDtoClass == getClassByClassName("FullResidentApartmentCommunityDto")){
                communityDto = new FullResidentApartmentCommunityDto();
                ((FullResidentApartmentCommunityDto) communityDto).setManagers(
                        (List<FullResidentApartmentCommunityManagerDto>)(List<?>)
                                UserConverter.convertEntityListToDtoList(community.getManagers(), FullResidentApartmentCommunityManagerDto.class));
            }
            communityDto.setAddress(community.getAddress());
            communityDto.setName(community.getName());
            communityDto.setId(community.getId());
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return communityDto;
    }

    private static Class getClassByClassName(String className) throws ClassNotFoundException {
        return Class.forName("com.waelawada.learn.springboot.dto.community."+className);
    }


    public static <T extends CommunityDto, U extends Community> List<U> convertDtoListToEntityList(List<T> communityDtos){
        List<U> communities = new LinkedList<U>();
        for (CommunityDto communityDto : communityDtos) {
            communities.add((U) convertDtoToEntity(communityDto));
        }
        return communities;
    }

    public static List<CommunityDto> convertEntityListToDtoList(List<Community> communities, Class<? extends CommunityDto> targetClass){
        List<CommunityDto> communityDtos = new LinkedList<CommunityDto>();
        for (Community community : communities) {
            communityDtos.add(convertEntityToDto(community, targetClass));
        }
        return communityDtos;
    }
}
