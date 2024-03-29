package com.waelawada.learn.springboot.converters;

import com.waelawada.learn.springboot.domain.community.Apartment;
import com.waelawada.learn.springboot.domain.users.ResidentUser;
import com.waelawada.learn.springboot.dto.apartments.*;
import com.waelawada.learn.springboot.dto.community.FullApartmentCommunityDto;
import com.waelawada.learn.springboot.dto.community.FullResidentApartmentCommunityDto;
import com.waelawada.learn.springboot.dto.users.FullApartmentResidentDto;
import com.waelawada.learn.springboot.dto.users.FullCommunityApartmentResidentDto;
import com.waelawada.learn.springboot.dto.users.FullManagerCommunityApartmentResidentDto;

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
        Apartment apartment = new Apartment();
        apartment.setId(apartmentDto.getId());
        apartment.setApartmentId(apartmentDto.getApartmentId());
        apartment.setMonthlyRent(apartmentDto.getMonthlyRent());
        apartment.setAddress(apartmentDto.getAddress());
        if(apartmentDto instanceof FullCommunityApartmentDto){
            apartment.setResidentUser((ResidentUser)UserConverter.convertDtoToEntity(((FullCommunityApartmentDto) apartmentDto).getResident()));
        }
        else if (apartmentDto instanceof FullManagerCommunityApartmentDto){
            apartment.setResidentUser((ResidentUser)UserConverter.convertDtoToEntity(((FullManagerCommunityApartmentDto) apartmentDto).getResident()));
        }
        else if (apartmentDto instanceof FullResidentApartmentDto){
            apartment.setCommunity(CommunityConverter.convertDtoToEntity(((FullResidentApartmentDto) apartmentDto).getCommunity()));
        }
        return apartment;
    }

    private static Class getClassFromClassName(String className) throws ClassNotFoundException {
        return Class.forName("com.waelawada.learn.springboot.dto.apartments."+className);
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
