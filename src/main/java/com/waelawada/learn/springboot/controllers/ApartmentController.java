package com.waelawada.learn.springboot.controllers;

import com.waelawada.learn.springboot.converters.ApartmentConverter;
import com.waelawada.learn.springboot.domain.community.Apartment;
import com.waelawada.learn.springboot.domain.users.ResidentUser;
import com.waelawada.learn.springboot.dto.apartments.ApartmentDto;
import com.waelawada.learn.springboot.dto.apartments.FullApartmentDto;
import com.waelawada.learn.springboot.services.ApartmentService;
import com.waelawada.learn.springboot.services.CommunityService;
import com.waelawada.learn.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by waelawada on 2/21/15.
 */
@RestController
@RequestMapping("/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<? extends ApartmentDto> getAllApartments(){
        return ApartmentConverter.convertEntityListToDtoList(apartmentService.findAll(), FullApartmentDto.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApartmentDto getApartment(@PathVariable Long id){
        return ApartmentConverter.convertEntityToDto(apartmentService.findById(id), FullApartmentDto.class);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ApartmentDto saveApartment(Apartment apartment){
        if(apartment.getCommunity() != null && apartment.getCommunity().getId() > 0){
            apartment.setCommunity(communityService.findById(apartment.getCommunity().getId()));
        }
        if(apartment.getResidentUser() != null && apartment.getResidentUser().getId() > 0){
            ResidentUser resident = (ResidentUser)userService.findById(apartment.getResidentUser().getId());
            apartment.setResidentUser(resident);
        }
        Apartment savedApartment = apartmentService.save(apartment);
        return ApartmentConverter.convertEntityToDto(savedApartment, FullApartmentDto.class);
    }

}
