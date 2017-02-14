package com.example.service;

import com.example.domain.Ad;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Teddy on 2017-02-08.
 */
public interface AdService {
    Ad findAdById(long id);
    Ad findAdByUserId(long userId);
    Ad addAd(Ad ad);
    Page<Ad> getAllAds(int pageNr);
    List<Ad> searchAdWith(String searchString);
}
