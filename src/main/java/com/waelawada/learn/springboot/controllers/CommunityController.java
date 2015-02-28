package com.waelawada.learn.springboot.controllers;

import com.waelawada.learn.springboot.converters.ApartmentConverter;
import com.waelawada.learn.springboot.converters.CommunityConverter;
import com.waelawada.learn.springboot.converters.UserConverter;
import com.waelawada.learn.springboot.domain.community.Apartment;
import com.waelawada.learn.springboot.domain.community.Community;
import com.waelawada.learn.springboot.domain.users.ManagerUser;
import com.waelawada.learn.springboot.dto.apartments.ApartmentDto;
import com.waelawada.learn.springboot.dto.apartments.FullApartmentDto;
import com.waelawada.learn.springboot.dto.community.CommunityDto;
import com.waelawada.learn.springboot.dto.community.FullCommunityDto;
import com.waelawada.learn.springboot.dto.users.FullManagerDto;
import com.waelawada.learn.springboot.dto.users.UserDto;
import com.waelawada.learn.springboot.exception.ApplicationError;
import com.waelawada.learn.springboot.exception.apartment.ApartmentNotFoundException;
import com.waelawada.learn.springboot.exception.community.CommunityNotFoundException;
import com.waelawada.learn.springboot.services.ApartmentService;
import com.waelawada.learn.springboot.services.CommunityService;
import com.waelawada.learn.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.waelawada.learn.springboot.converters.CommunityConverter.convertEntityToDto;

/**
 * Created by waelawada on 2/21/15.
 */
@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApartmentService apartmentService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Community addCommunity(Community community){
        return communityService.save(community);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommunityDto getCommunity(@PathVariable Long id){
        Community community = communityService.findById(id);
        if(community == null) throw new CommunityNotFoundException(id);
        return convertEntityToDto(community, FullCommunityDto.class);
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public List<CommunityDto> getAllCommunities(){
        return CommunityConverter.convertEntityListToDtoList(communityService.findAll(), FullCommunityDto.class);
    }

    @RequestMapping(value = "{id}/managers/", method = RequestMethod.POST)
    public CommunityDto addManagerToCommunity(@PathVariable Long id, ManagerUser managerUser){
        Community community = communityService.findById(id);
        if(managerUser!=null && managerUser.getId()>0){
            managerUser = (ManagerUser)userService.findById(managerUser.getId());
        }
        else {
            managerUser = (ManagerUser) userService.save(managerUser);
        }
        community.getManagers().add(managerUser);
        return convertEntityToDto(communityService.save(community), FullCommunityDto.class);
    }

    @RequestMapping(value = "{id}/managers/", method = RequestMethod.DELETE)
    public CommunityDto removeManagerFromCommunity(@PathVariable Long id, @RequestParam Long managerId){
        ManagerUser managerUser = (ManagerUser) userService.findById(managerId);
        Community community = communityService.findById(id);
        community.getManagers().remove(managerUser);
        community = communityService.save(community);
        return convertEntityToDto(community, FullCommunityDto.class);
    }

    @RequestMapping(value = "{id}/managers/", method = RequestMethod.GET)
    public List<UserDto> getAllManagersForACommunity(@PathVariable Long id){
        Community community = communityService.findById(id);
        return UserConverter.convertEntityListToDtoList(community.getManagers(), FullManagerDto.class);
    }

    @RequestMapping(value = "{id}/apartments/", method = RequestMethod.POST)
    public CommunityDto addApartmentToCommunity(@PathVariable Long communityId, Apartment apartment){
        if(apartment!=null && apartment.getId()>0){
            apartment = apartmentService.findById(apartment.getId());
        }
        else{
            apartment = apartmentService.save(apartment);
        }
        Community community = communityService.findById(communityId);
        community.getApartments().add(apartment);
        return convertEntityToDto(communityService.save(community), FullCommunityDto.class);
    }

    @RequestMapping(value = "{id}/apartments/", method = RequestMethod.DELETE)
    public CommunityDto removeApartmentFromCommunity(@PathVariable Long id, @RequestParam Long apartmentId){
        Apartment apartment = apartmentService.findById(apartmentId);
        Community community = communityService.findById(id);
        community.getApartments().remove(apartment);
        return convertEntityToDto(communityService.save(community), FullCommunityDto.class);
    }

    @RequestMapping(value = "{id}/apartments/", method = RequestMethod.GET)
    public List<ApartmentDto> getAllApartmentForACommunity(@PathVariable Long id){
        Community community = communityService.findById(id);
        return ApartmentConverter.convertEntityListToDtoList(community.getApartments(), FullApartmentDto.class);
    }

    @ExceptionHandler(value = CommunityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApplicationError communityNotFound(CommunityNotFoundException cpe){
        Long communityId = cpe.getObjectId();
        String externalCommunityId= cpe.getExternalObjectId();
        String messageId = (externalCommunityId!=null ? externalCommunityId : String.valueOf(communityId));
        return new ApplicationError(21, "Community "+messageId+" not found");
    }
}
