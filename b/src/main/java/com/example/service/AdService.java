package com.example.service;

import com.example.domain.Ad;

/**
 * Created by Teddy on 2017-02-08.
 */
public interface AdService {
    Ad findAdById(long id);
    Ad findAdByUserId(long userId);
    Ad addAd(Ad ad);
}
