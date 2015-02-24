package com.waelawada.learn.springboot.controllers;

import com.waelawada.learn.springboot.converters.CommunityConverter;
import com.waelawada.learn.springboot.domain.community.Apartment;
import com.waelawada.learn.springboot.domain.community.Community;
import com.waelawada.learn.springboot.domain.users.ManagerUser;
import com.waelawada.learn.springboot.dto.community.CommunityDto;
import com.waelawada.learn.springboot.dto.community.FullCommunityDto;
import com.waelawada.learn.springboot.services.ApartmentService;
import com.waelawada.learn.springboot.services.CommunityService;
import com.waelawada.learn.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return CommunityConverter.convertEntityToDto(communityService.findById(id), FullCommunityDto.class);
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public List<? extends CommunityDto> getAllCommunities(){
        return CommunityConverter.convertEntityListToDtoList(communityService.findAll(), FullCommunityDto.class);
    }

    @RequestMapping(value = "{id}/managers/", method = RequestMethod.POST)
    public Community addManagerToCommunity(@PathVariable Long id, ManagerUser managerUser){
        Community community = communityService.findById(id);
        if(managerUser!=null && managerUser.getId()>0){
            managerUser = (ManagerUser)userService.findById(managerUser.getId());
        }
        else {
            managerUser = (ManagerUser) userService.save(managerUser);
        }
        community.getManagers().add(managerUser);
        return communityService.save(community);
    }

    @RequestMapping(value = "{id}/managers/", method = RequestMethod.DELETE)
    public Community removeManagerFromCommunity(@PathVariable Long id, @RequestParam Long managerId){
        ManagerUser managerUser = (ManagerUser) userService.findById(managerId);
        Community community = communityService.findById(id);
        community.getManagers().remove(managerUser);
        community = communityService.save(community);
        return community;
    }

    @RequestMapping(value = "{id}/managers/", method = RequestMethod.GET)
    public List<ManagerUser> getAllManagersForACommunity(@PathVariable Long id){
        Community community = communityService.findById(id);
        return community.getManagers();
    }

    @RequestMapping(value = "{id}/apartments/", method = RequestMethod.POST)
    public Community addApartmentToCommunity(@PathVariable Long communityId, Apartment apartment){
        if(apartment!=null && apartment.getId()>0){
            apartment = apartmentService.findById(apartment.getId());
        }
        else{
            apartment = apartmentService.save(apartment);
        }
        Community community = communityService.findById(communityId);
        community.getApartments().add(apartment);
        return communityService.save(community);
    }

    @RequestMapping(value = "{id}/apartments/", method = RequestMethod.DELETE)
    public Community removeApartmentFromCommunity(@PathVariable Long id, @RequestParam Long apartmentId){
        Apartment apartment = apartmentService.findById(apartmentId);
        Community community = communityService.findById(id);
        community.getApartments().remove(apartment);
        return communityService.save(community);
    }

    @RequestMapping(value = "{id}/apartments/", method = RequestMethod.GET)
    public List<Apartment> getAllApartmentForACommunity(@PathVariable Long id){
        Community community = communityService.findById(id);
        return community.getApartments();
    }
}
