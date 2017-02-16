package com.example.service.impl;

import com.example.domain.Photo;
import com.example.repositories.PhotoCustomRepository;
import com.example.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Teddy on 2017-02-10.
 */
@Transactional
@Service
public class PhotoServiceImpl implements PhotoService{

    @Autowired
    private PhotoService photoService;
    @Autowired
    private PhotoCustomRepository photoCustomRepository;

    @Override
    public Photo findPhotoById(long id) {
        return photoService.findPhotoById(id);
    }

    @Override
    public List<Photo> getPhotosByAdId(long adId) {
        return photoCustomRepository.getPhotosByAdId(adId);
    }
    //ska hämta alla bilder efter annons id. Se TransactionRepositoryImpl under repositories/impl mappen för att göra egna querys.

}
