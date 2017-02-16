package com.example.service;

import com.example.domain.Ad;

import java.util.List;

/**
 * Created by Teddy on 2017-02-08.
 */
public interface AdService {
    Ad findAdById(long id);
    Ad findAdByUserId(long userId);
    Ad addAd(Ad ad);
    List<Ad> getAllAds(int pageNr);
}
