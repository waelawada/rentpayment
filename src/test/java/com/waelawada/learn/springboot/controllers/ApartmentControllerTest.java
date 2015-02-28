package com.waelawada.learn.springboot.controllers;

import com.waelawada.learn.springboot.Application;
import com.waelawada.learn.springboot.domain.community.Apartment;
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
@WebAppConfiguration
public class ApartmentControllerTest {

    private MockMvc mockMvc;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @InjectMocks
    private ApartmentController apartmentController;

    @Mock
    private ApartmentService apartmentService;

    @Mock
    private CommunityService communityService;

    @Mock
    private UserService userService;

    @Before
    public void setup() {
        Apartment apartment = Apartments.getApartment();
        apartment.setId(1L);
        when(apartmentService.findById(1L)).thenReturn(apartment);

        mockMvc = MockMvcBuilders.standaloneSetup(apartmentController).build();
    }

    @Test
    public void testGetExistingApartment() throws Exception {
        mockMvc.perform(get("/apartment/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

        System.out.println(apartmentService.findById(1L));

    }


}
