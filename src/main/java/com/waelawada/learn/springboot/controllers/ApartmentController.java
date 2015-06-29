package com.waelawada.learn.springboot.controllers;

import com.waelawada.learn.springboot.converters.ApartmentConverter;
import com.waelawada.learn.springboot.domain.community.Apartment;
import com.waelawada.learn.springboot.domain.users.ResidentUser;
import com.waelawada.learn.springboot.dto.ApartmentDto;
import com.waelawada.learn.springboot.dto.request.apartment.RequestApartmentDto;
import com.waelawada.learn.springboot.dto.response.apartment.FullApartmentDto;
import com.waelawada.learn.springboot.exception.ApplicationError;
import com.waelawada.learn.springboot.exception.apartment.ApartmentNotFoundException;
import com.waelawada.learn.springboot.jsondoc.JsonDocConstants;
import com.waelawada.learn.springboot.services.ApartmentService;
import com.waelawada.learn.springboot.services.CommunityService;
import com.waelawada.learn.springboot.services.UserService;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiVerb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by waelawada on 2/21/15.
 */
@Api(name = "Apartment", description = "Access The Apartment Service")
@RestController
@RequestMapping("/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserService userService;

    @ApiMethod(id = JsonDocConstants.APARTMENT_LIST_GET, description= "Gets a List of All Apartments", produces = "application/json", verb = ApiVerb.GET)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ApiResponseObject List<? extends ApartmentDto> getAllApartments(){
        return ApartmentConverter.convertEntityListToDtoList(apartmentService.findAll(), FullApartmentDto.class);
    }

    @ApiMethod(id = JsonDocConstants.APARTMENT_GET, description = "Gets information about a specific apartment", produces = "application/json", verb = ApiVerb.GET)
    @ApiParams(pathparams = {@ApiPathParam(name = "id", description = "The id of the apartment requested",clazz = Long.class)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ApiResponseObject ApartmentDto getApartment(@PathVariable Long id){
        Apartment apartment = apartmentService.findById(id);
        if(apartment == null) throw new ApartmentNotFoundException(id);
        return ApartmentConverter.convertEntityToDto(apartment, FullApartmentDto.class);
    }

    @ApiMethod(id = JsonDocConstants.APARTMENT_ADD, description = "Saves a new Apartment", produces = "application/json", verb = ApiVerb.POST)
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ApiResponseObject ApartmentDto saveApartment(@ApiBodyObject(clazz = RequestApartmentDto.class) @RequestBody RequestApartmentDto apartment){
        Apartment apartmentEntity = ApartmentConverter.convertDtoToEntity(apartment);
        Apartment savedApartment = apartmentService.save(apartmentEntity);
        return ApartmentConverter.convertEntityToDto(savedApartment, FullApartmentDto.class);
    }

}
