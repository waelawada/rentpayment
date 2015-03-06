package com.waelawada.learn.springboot.controllers;

import com.waelawada.learn.springboot.Application;
import com.waelawada.learn.springboot.converters.ApartmentConverter;
import com.waelawada.learn.springboot.dto.apartments.FullApartmentDto;
import com.waelawada.learn.springboot.fixtures.ApartmentFixture;
import com.waelawada.learn.springboot.services.ApartmentService;
import com.waelawada.learn.springboot.services.CommunityService;
import com.waelawada.learn.springboot.services.UserService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    @Autowired
    private ApartmentController apartmentController;

    @Mock
    private ApartmentService apartmentService;

    @Mock
    private CommunityService communityService;

    @Mock
    private UserService userService;

    @Before
    public void setup() {

        System.out.println(apartmentService);
        mockMvc = MockMvcBuilders.standaloneSetup(apartmentController).build();
    }

    @Test
    public void testGetExistingApartment() throws Exception {
//        when(apartmentService.findById(1L)).thenReturn(ApartmentFixture.FULL_APARTMENT_1);

        FullApartmentDto returnedApartment =
                (FullApartmentDto)ApartmentConverter.convertEntityToDto(apartmentService.findById(1L), FullApartmentDto.class);

        mockMvc.perform(get("/apartment/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.parseInt(String.valueOf(returnedApartment.getId())))))
                .andExpect(jsonPath("$.apartmentId", is(returnedApartment.getApartmentId())))
                .andExpect(jsonPath("$.resident.firstName", is(returnedApartment.getResident().getFirstName())));
    }


    @Test
    public void testGetNonExistingApartment() throws Exception {
//        when(apartmentService.findById(2L)).thenReturn(null);

        mockMvc.perform(get("/apartment/{id}", 2)).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(11)))
                .andExpect(jsonPath("$.description", is("Apartment 2 not found")));
    }

    @Test
    public void insertApartment() throws Exception{



    }


}
