package com.example.service.impl;

import com.example.domain.Ad;
import com.example.domain.Category;
import com.example.domain.User;
import com.example.repositories.AdRepository;
import com.example.service.AdService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * Created by dani on 2017-02-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AdServiceImplTest {

    @Mock
    private AdRepository adRepository;


    @InjectMocks
    private AdService adService = new AdServiceImpl();

    @Before
    public void setUp(){ MockitoAnnotations.initMocks(this); }

    // cant test getAllAdss because of Page object...

    @Test
    public void addAd() throws Exception {

        User user = new User();
        user.setId(1L);
        user.setUsername("anders");
        user.setPassword("$2a$06$CDyAlnH2sR4taKM/ZoRIguvnY6/5rKHS.uDpJGvI8B.LQqD.OWQ5a");
        user.setEnabled(true);
        user.setEmail("anders@kth.se");

        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("category");

        Ad ad = new Ad();
        ad.setAdId(1);
        ad.setUser(user);
        ad.setCategory(category);
        ad.setTitle("first ad");
        ad.setDescription("first ad on website!");

        when(adRepository.save(ad)).thenReturn(ad);
        Ad adCheck = adService.addAd(ad);

        Assert.assertEquals(ad,adCheck);
    }
}