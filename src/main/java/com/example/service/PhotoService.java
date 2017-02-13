package com.example.service;

import com.example.domain.Photo;

import java.util.List;

/**
 * Created by Teddy on 2017-02-10.
 */
public interface PhotoService {
    Photo findPhotoById(long id);
    List<Photo> getPhotosByAdId(long adId);

}
