package com.waelawada.learn.springboot.converters;

import com.waelawada.learn.springboot.domain.community.Apartment;
import com.waelawada.learn.springboot.domain.users.ResidentUser;
import com.waelawada.learn.springboot.dto.ApartmentDto;
import com.waelawada.learn.springboot.dto.response.apartment.FullApartmentCommunityDto;
import com.waelawada.learn.springboot.dto.response.manager.FullManagerCommunityApartmentDto;
import com.waelawada.learn.springboot.dto.response.resident.FullResidentApartmentCommunityDto;
import com.waelawada.learn.springboot.dto.response.apartment.FullApartmentDto;
import com.waelawada.learn.springboot.dto.response.apartment.FullApartmentResidentDto;
import com.waelawada.learn.springboot.dto.response.community.FullCommunityApartmentDto;
import com.waelawada.learn.springboot.dto.response.community.FullCommunityApartmentResidentDto;
import com.waelawada.learn.springboot.dto.response.resident.FullResidentApartmentDto;
import com.waelawada.learn.springboot.dto.response.manager.FullManagerCommunityApartmentResidentDto;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by waelawada on 2/21/15.
 */
public class ApartmentConverter {

    public static ApartmentDto convertEntityToDto(Apartment apartment, Class apartmentDtoClass){
        ApartmentDto apartmentDto = null;
        if(apartment==null) return null;
        try {
            if(apartmentDtoClass == getClassFromClassName("FullApartmentDto")){
                apartmentDto = new FullApartmentDto();
                ((FullApartmentDto) apartmentDto).setResident((FullApartmentResidentDto)UserConverter.convertEntityToDto(apartment.getResidentUser(), FullApartmentResidentDto.class));
                ((FullApartmentDto) apartmentDto).setCommunity((FullApartmentCommunityDto)CommunityConverter.convertEntityToDto(apartment.getCommunity(), FullApartmentCommunityDto.class));
            }
            else if(apartmentDtoClass == getClassFromClassName("FullCommunityApartmentDto")){
                apartmentDto = new FullCommunityApartmentDto();
                ((FullCommunityApartmentDto) apartmentDto).setResident((FullCommunityApartmentResidentDto)UserConverter.convertEntityToDto(apartment.getResidentUser(), FullCommunityApartmentResidentDto.class));
            }
            else if(apartmentDtoClass == getClassFromClassName("FullManagerCommunityApartmentDto")){
                apartmentDto = new FullManagerCommunityApartmentDto();
                ((FullManagerCommunityApartmentDto) apartmentDto).setResident((FullManagerCommunityApartmentResidentDto)UserConverter.convertEntityToDto(apartment.getResidentUser(), FullManagerCommunityApartmentResidentDto.class));
            }
            else if(apartmentDtoClass == getClassFromClassName("FullResidentApartmentDto")){
                apartmentDto = new FullResidentApartmentDto();
                ((FullResidentApartmentDto) apartmentDto).setCommunity((FullResidentApartmentCommunityDto)CommunityConverter.convertEntityToDto(apartment.getCommunity(), FullResidentApartmentCommunityDto.class));
            }
            apartmentDto.setId(apartment.getId());
            apartmentDto.setMonthlyRent(apartment.getMonthlyRent());
            apartmentDto.setAddress(apartment.getAddress());
            apartmentDto.setApartmentId(apartment.getApartmentId());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return apartmentDto;
    }

    public static Apartment convertDtoToEntity(ApartmentDto apartmentDto){
        if(apartmentDto == null) return null;
        Apartment.Builder apartmentBuilder = Apartment.newBuilder();
        apartmentBuilder.id(apartmentDto.getId());
        apartmentBuilder.apartmentId(apartmentDto.getApartmentId());
        apartmentBuilder.monthlyRent(apartmentDto.getMonthlyRent());
        apartmentBuilder.address(apartmentDto.getAddress());
        if(apartmentDto instanceof FullCommunityApartmentDto){
            apartmentBuilder.residentUser((ResidentUser)UserConverter.convertDtoToEntity(((FullCommunityApartmentDto) apartmentDto).getResident()));
        }
        else if (apartmentDto instanceof FullManagerCommunityApartmentDto){
            apartmentBuilder.residentUser((ResidentUser)UserConverter.convertDtoToEntity(((FullManagerCommunityApartmentDto) apartmentDto).getResident()));
        }
        else if (apartmentDto instanceof FullResidentApartmentDto){
            apartmentBuilder.community(CommunityConverter.convertDtoToEntity(((FullResidentApartmentDto) apartmentDto).getCommunity()));
        }
        return apartmentBuilder.build();
    }

    private static Class getClassFromClassName(String className) throws ClassNotFoundException {
        return Class.forName("com.waelawada.learn.springboot.dto.response.apartment."+className);
    }

    public static <T extends ApartmentDto, U extends Apartment> List<U> convertDtoListToEntityList(List<T> apartmentDtos){
        List<U> apartments = new LinkedList<U>();
        for (ApartmentDto apartmentDto : apartmentDtos) {
            apartments.add((U)convertDtoToEntity(apartmentDto));
        }
        return apartments;
    }

    public static List<ApartmentDto> convertEntityListToDtoList(List<Apartment> apartments, Class<? extends ApartmentDto> targetClass){
        List<ApartmentDto> apartmentDtos = new LinkedList<ApartmentDto>();
        for (Apartment apartment : apartments) {
            apartmentDtos.add(convertEntityToDto(apartment, targetClass));
        }
        return apartmentDtos;
    }

}
