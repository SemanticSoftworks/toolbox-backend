package com.example.service.impl;

import com.example.domain.Ad;
import com.example.repositories.AdRepository;
import com.example.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Teddy on 2017-02-08.
 */
@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository adRepository;

    @Override
    public Ad findAdById(long id) {
        return adRepository.findByAdId(id);
    }

    @Override
    public Ad findAdByUserId(long userId) {
        return adRepository.findByUser(userId);
    }

    public Ad addAd(Ad ad)
    {
        return adRepository.save(ad);
    }
}
