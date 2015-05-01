package com.waelawada.learn.springboot.controllers;

import com.waelawada.learn.springboot.converters.ApartmentConverter;
import com.waelawada.learn.springboot.converters.CommunityConverter;
import com.waelawada.learn.springboot.converters.UserConverter;
import com.waelawada.learn.springboot.domain.community.Apartment;
import com.waelawada.learn.springboot.domain.community.Community;
import com.waelawada.learn.springboot.domain.users.ManagerUser;
import com.waelawada.learn.springboot.dto.ApartmentDto;
import com.waelawada.learn.springboot.dto.response.apartment.FullApartmentDto;
import com.waelawada.learn.springboot.dto.CommunityDto;
import com.waelawada.learn.springboot.dto.response.community.FullCommunityDto;
import com.waelawada.learn.springboot.dto.response.manager.FullManagerDto;
import com.waelawada.learn.springboot.dto.UserDto;
import com.waelawada.learn.springboot.exception.ApplicationError;
import com.waelawada.learn.springboot.exception.apartment.ApartmentNotFoundException;
import com.waelawada.learn.springboot.exception.community.CommunityNotFoundException;
import com.waelawada.learn.springboot.exception.user.ManagerNotFoundException;
import com.waelawada.learn.springboot.exception.user.UserNotFoundException;
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

import static com.waelawada.learn.springboot.converters.CommunityConverter.convertEntityToDto;

/**
 * Created by waelawada on 2/21/15.
 */
@Api(name = "Community", description = "Access the community service")
@RestController
@RequestMapping("/community")
public class CommunityController extends GenericController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApartmentService apartmentService;

    @ApiMethod(description = "Add a new community", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, verb = ApiVerb.POST)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CommunityDto addCommunity(@ApiBodyObject(clazz = CommunityDto.class) @RequestBody CommunityDto community){
        return CommunityConverter.convertEntityToDto(
                communityService.save(CommunityConverter.convertDtoToEntity(community)), FullCommunityDto.class);
    }

    @ApiMethod(description = "Gets details about an existing community", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, verb = ApiVerb.GET)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommunityDto getCommunity(@ApiPathParam(name = "id", description = "The Id of the community") @PathVariable Long id){
        Community community = communityService.findById(id);
        if(community == null) throw new CommunityNotFoundException(id);
        return convertEntityToDto(community, FullCommunityDto.class);
    }

    @ApiMethod(description = "Get a list of all communities", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, verb = ApiVerb.GET)
    @RequestMapping(value="/", method = RequestMethod.GET)
    public List<CommunityDto> getAllCommunities(){
        return CommunityConverter.convertEntityListToDtoList(communityService.findAll(), FullCommunityDto.class);
    }

    @ApiMethod(description = "Adds a manager to a community", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, verb = ApiVerb.POST)
    @RequestMapping(value = "{id}/managers/", method = RequestMethod.POST)
    public CommunityDto addManagerToCommunity(
            @ApiPathParam(name = "id", description = "The Id of the community") @PathVariable Long id,
            @ApiQueryParam(name = "managerId", description = "The Id of the manager") @RequestParam Long managerId){
        Community community = communityService.findById(id);
        ManagerUser managerUser = (ManagerUser) userService.findById(managerId);
        if(community == null) throw new CommunityNotFoundException(id);
        if(managerUser == null) throw new ManagerNotFoundException(managerId);
        community.getManagers().add(managerUser);
        return convertEntityToDto(communityService.save(community), FullCommunityDto.class);
    }

    @ApiMethod(description = "Removes a manager from a community", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, verb = ApiVerb.DELETE)
    @RequestMapping(value = "{id}/managers/", method = RequestMethod.DELETE)
    public CommunityDto removeManagerFromCommunity(
            @ApiPathParam(name = "id", description = "The Id of the community") @PathVariable Long id,
            @ApiQueryParam(name = "managerId", description = "The Id of the manager") @RequestParam Long managerId){
        ManagerUser managerUser = (ManagerUser) userService.findById(managerId);
        if(managerUser == null) throw new UserNotFoundException(managerId);
        Community community = communityService.findById(id);
        if(community == null) throw new CommunityNotFoundException(id);
        community.getManagers().remove(managerUser);
        community = communityService.save(community);
        return convertEntityToDto(community, FullCommunityDto.class);
    }

    @ApiMethod(description = "Get a list of managers for a community", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, verb = ApiVerb.GET)
    @RequestMapping(value = "{id}/managers/", method = RequestMethod.GET)
    public List<UserDto> getAllManagersForACommunity(
            @ApiPathParam(name = "id", description = "The Id of the community") @PathVariable Long id){
        Community community = communityService.findById(id);
        return UserConverter.convertEntityListToDtoList(community.getManagers(), FullManagerDto.class);
    }

    @ApiMethod(description = "Add an apartment to a community", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, verb = ApiVerb.POST)
    @RequestMapping(value = "{id}/apartments/", method = RequestMethod.POST)
    public CommunityDto addApartmentToCommunity(
            @ApiPathParam(description = "The Id of the community",name = "communityId") @PathVariable Long communityId,
            @ApiQueryParam(description = "The Id of the apartment", name = "apartmentId") @RequestParam Long apartmentId){
        Apartment apartment = apartmentService.findById(apartmentId);
        if(apartment == null) throw new ApartmentNotFoundException(apartmentId);
        Community community = communityService.findById(communityId);
        if(community == null) throw new CommunityNotFoundException(communityId);
        community.getApartments().add(apartment);
        return convertEntityToDto(communityService.save(community), FullCommunityDto.class);
    }

    @ApiMethod(description = "Delete ana apartment from a community", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, verb = ApiVerb.DELETE)
    @RequestMapping(value = "{id}/apartments/", method = RequestMethod.DELETE)
    public CommunityDto removeApartmentFromCommunity(
            @ApiPathParam(name = "id", description = "The Id of the community") @PathVariable Long id,
            @ApiQueryParam(name = "apartmentId", description = "The Id of the apartment") @RequestParam Long apartmentId){
        Apartment apartment = apartmentService.findById(apartmentId);
        if(apartment == null) throw new ApartmentNotFoundException(apartmentId);
        Community community = communityService.findById(id);
        if(community == null) throw new CommunityNotFoundException(id);
        community.getApartments().remove(apartment);
        return convertEntityToDto(communityService.save(community), FullCommunityDto.class);
    }

    @ApiMethod(description = "Get All apartments in a community", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, verb = ApiVerb.GET)
    @RequestMapping(value = "{id}/apartments/", method = RequestMethod.GET)
    public List<ApartmentDto> getAllApartmentForACommunity(
            @ApiPathParam(name = "id", description = "The Id of the community") @PathVariable Long id){
        Community community = communityService.findById(id);
        return ApartmentConverter.convertEntityListToDtoList(community.getApartments(), FullApartmentDto.class);
    }
}
