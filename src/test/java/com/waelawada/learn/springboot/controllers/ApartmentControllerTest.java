package com.waelawada.learn.springboot.controllers;

import com.waelawada.learn.springboot.Application;
import com.waelawada.learn.springboot.converters.ApartmentConverter;
import com.waelawada.learn.springboot.domain.community.Apartment;
import com.waelawada.learn.springboot.dto.apartments.ApartmentDto;
import com.waelawada.learn.springboot.dto.apartments.FullApartmentDto;
import com.waelawada.learn.springboot.fixtures.ApartmentFixture;
import com.waelawada.learn.springboot.services.ApartmentService;
import com.waelawada.learn.springboot.services.CommunityService;
import com.waelawada.learn.springboot.services.UserService;
import com.waelawada.learn.springboot.util.Apartments;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by waelawada on 2/26/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
public class ApartmentControllerTest {

    private MockMvc mockMvc;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @InjectMocks
    private ApartmentController apartmentController;

    @Autowired
    private ApartmentService apartmentService;

    @Mock
    private CommunityService communityService;

    @Mock
    private UserService userService;

    @Before
    public void setup() {
        when(apartmentService.findById(1L)).thenReturn(ApartmentFixture.APARTMENT_1);
        System.out.println(apartmentService);
        mockMvc = MockMvcBuilders.standaloneSetup(apartmentController).build();
    }

    @Test
    public void testGetExistingApartment() throws Exception {
        FullApartmentDto returnedApartment =
                (FullApartmentDto)ApartmentConverter.convertEntityToDto(apartmentService.findById(1L), FullApartmentDto.class);

        mockMvc.perform(get("/apartment/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(returnedApartment.getId())))
                .andExpect(jsonPath("$.apartmentId", is(returnedApartment.getApartmentId())))
                .andExpect(jsonPath("$.resident[0].firstname", is(returnedApartment.getResident().getFirstName())))
        ;

        System.out.println(apartmentService.findById(1L));

    }


}
