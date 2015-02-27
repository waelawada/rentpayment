package com.waelawada.learn.springboot.dto.community;

import com.waelawada.learn.springboot.domain.users.User;
import com.waelawada.learn.springboot.dto.users.FullApartmentCommunityManagerDto;
import com.waelawada.learn.springboot.dto.users.UserDto;

import java.util.List;

/**
 * Created by waelawada on 2/21/15.
 */
public class FullApartmentCommunityDto extends CommunityDto {

    private List<? extends UserDto> managers;

    public List<? extends UserDto> getManagers() {
        return managers;
    }

    public void setManagers(List<? extends UserDto> managers) {
        this.managers = managers;
    }
}
