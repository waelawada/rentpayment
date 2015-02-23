package com.waelawada.learn.springboot.controllers;

import com.waelawada.learn.springboot.converters.CommunityConverter;
import com.waelawada.learn.springboot.domain.community.Community;
import com.waelawada.learn.springboot.dto.community.CommunityDto;
import com.waelawada.learn.springboot.dto.community.FullCommunityDto;
import com.waelawada.learn.springboot.services.CommunityService;
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

}
