package com.example.service.impl;

import com.example.domain.Ad;
import com.example.repositories.AdRepository;
import com.example.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Teddy on 2017-02-08.
 */
@Service
public class AdServiceImpl implements AdService {
    private static final int PAGE_SIZE = 20;

    @Autowired
    private AdRepository adRepository;

    @Override
    public Ad findAdById(long id)
    {
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


    @Override
    public Page<Ad> getAllAds(int pageNr) {
        PageRequest pageRequest = new PageRequest(pageNr, PAGE_SIZE);
        return adRepository.findAll(pageRequest);
    }


}
