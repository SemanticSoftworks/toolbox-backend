package com.example.service.impl;

import com.example.domain.Ad;
import com.example.domain.Photo;
import com.example.repositories.PhotoCustomRepository;
import com.example.service.PhotoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by dani on 2017-02-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PhotoServiceImplTest {

    @Mock
    private PhotoCustomRepository photoCustomRepository;

    @InjectMocks
    private PhotoService photoService = new PhotoServiceImpl();

    @Test
    public void getPhotosByAdId() throws Exception {
        List<Photo> photoList = new ArrayList<>();
        Photo photo = new Photo();
        photo.setId(1);
        photo.setPhotobyte("".getBytes());

        Photo p = new Photo();
        p.setId(2);
        p.setPhotobyte("23".getBytes());

        photoList.add(p);
        photoList.add(photo);

        Ad ad = new Ad();
        ad.setAdId(1);
        ad.setTitle("ad");
        ad.setDescription("first ad");
        ad.setPhotos(photoList);

        when(photoCustomRepository.getPhotosByAdId(1)).thenReturn(photoList);
        List<Photo> returnedPhotos = photoService.getPhotosByAdId(1);
        Assert.assertEquals(photoList.size() , returnedPhotos.size());
    }

}