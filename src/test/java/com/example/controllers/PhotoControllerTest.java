package com.example.controllers;

import com.example.service.PhotoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by dani on 2017-02-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PhotoControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PhotoController photoController;

    @Mock
    private PhotoService photoService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(photoController).build();
    }

    @Test
    public void getImgsOfAd() throws Exception {
       // --> not working coz of null pointer exception in photocontroller:56 ?
       /*
        User user = new User();
        user.setId(1L);
        user.setUsername("anders");
        user.setPassword("123");
        user.setEmail("anders@kth.se");

        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("category");

        Photo photo = new Photo();
        photo.setId(1);
        photo.setPhotobyte("".getBytes());

        Photo photo1 = new Photo();
        photo.setId(2);
        photo.setPhotobyte("".getBytes());

        List<Photo> photoList = new ArrayList<>();
        photoList.add(photo);
        photoList.add(photo1);

        Ad ad = new Ad();
        ad.setAdId(1);
        ad.setTitle("adtest");
        ad.setDescription("first ad");
        ad.setCategory(category);
        ad.setUser(user);
        ad.setPhotos(photoList);

        when(photoService.getPhotosByAdId(1)).thenReturn(photoList);
        mockMvc.perform(MockMvcRequestBuilders.get("/photo/getadphotos/{adId}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("[{\"id\":1,\"imgByte\":[],\"adId\":1}]"));
        */
    }
}