package com.example.service.impl;

import com.example.domain.Photo;
import com.example.repositories.AdRepository;
import com.example.repositories.PhotoRepository;
import com.example.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Teddy on 2017-02-10.
 */
public class PhotoServiceImpl implements PhotoService{

    @Autowired
    private PhotoRepository photoRepository;
    //ska hämta alla bilder efter annons id. Se TransactionRepositoryImpl under repositories/impl mappen för att göra egna querys.
    public List<Photo> getAllPhotoByAdId(long adId)
    {
        return photoRepository();
    }
}
