package com.example.controllers;

import com.example.domain.Ad;
import com.example.domain.Category;
import com.example.domain.User;
import com.example.service.AdService;
import com.example.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dani on 2017-02-16.
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class AdControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AdController adController;

    @Mock
    private AdService adService;

    @Mock
    private UserService userService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adController).build();
    }

    @Test
    public void getAd() throws Exception{

        User user = new User();
        user.setId(1L);
        user.setUsername("anders");
        user.setPassword("123");
        user.setEmail("anders@kth.se");

        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("category");

        Ad ad = new Ad();
        ad.setAdId(1);
        ad.setTitle("test");
        ad.setDescription("first ad");
        ad.setCategory(category);
        ad.setUser(user);

        when(adService.findAdById(1)).thenReturn(ad);
        mockMvc.perform(MockMvcRequestBuilders.get("/ad/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("{\"adId\":1,\"category\":\"category\",\"title\":\"test\",\"description\":\"first ad\",\"duration\":null,\"user\":1}"));
    }

    @Test
    public void getAllAds() throws Exception {
        List<Ad> ads = new ArrayList<>();

        User user = new User();
        user.setId(1L);
        user.setUsername("anders");
        user.setPassword("123");
        user.setEmail("anders@kth.se");

        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("category");

        Ad ad1 = new Ad();
        ad1.setAdId(1);
        ad1.setUser(user);
        ad1.setCategory(category);
        ad1.setTitle("first");
        ad1.setDescription("first ad");

        Ad ad2 = new Ad();
        ad2.setAdId(2);
        ad2.setUser(user);
        ad2.setCategory(category);
        ad2.setTitle("second");
        ad2.setDescription("second ad");

        ads.add(ad1);
        ads.add(ad2);

        when(adService.getAllAds(1)).thenReturn(ads);
        mockMvc.perform(MockMvcRequestBuilders.get("/ad/getads/{pageNr}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("[{\"adId\":1,\"category\":\"category\",\"title\":\"first\",\"description\":\"first ad\",\"duration\":null,\"user\":1},{\"adId\":2,\"category\":\"category\",\"title\":\"second\",\"description\":\"second ad\",\"duration\":null,\"user\":1}]"));
    }
}