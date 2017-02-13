package com.example.controllers;

import com.example.domain.Ad;
import com.example.domain.Category;
import com.example.domain.Photo;
import com.example.domain.User;
import com.example.model.AdDTO;
import com.example.model.CategoryDTO;
import com.example.model.PhotoDTO;
import com.example.service.AdService;
import com.example.service.PhotoService;
import com.example.service.UserService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


/**
 * Created by Teddy on 2017-02-08.
 */
@RestController
@RequestMapping("/ad")
public class AdController {
    @Autowired
    private AdService adService;
    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AdDTO> getAd(@PathVariable Long id){

        AdDTO ad = toDTO(adService.findAdById(id));
        if(ad == null){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ad, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes={"application/json"})
    public ResponseEntity<AdDTO> addAd(@RequestBody AdDTO adDTO)
    {
        //AdDTO test = new AdDTO(-1,1, new CategoryDTO(new Long(1),"test"), "testAdd", "testDescription", Calendar.getInstance());

        if(adDTO == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        adService.addAd(toAd(adDTO));

        //AdDTO addedAdDTO = toDTO(adService.addAd(toAd(adDTO)));
        return new ResponseEntity<>(adDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/getads/{pageNr}", method = RequestMethod.GET)
    public ResponseEntity<List<AdDTO>> getAllAds(@PathVariable int pageNr)
    {
        List<AdDTO> resultList = convertToAdDTOList(adService.getAllAds(pageNr).getContent());
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    private List<Ad> convertToAdList(List<AdDTO> adList)
    {
        ArrayList<Ad> resultList = new ArrayList<Ad>();
        for(AdDTO ad : adList)
        {
            resultList.add(toAd(ad));
        }
        return resultList;
    }

    private List<AdDTO> convertToAdDTOList(List<Ad> adList)
    {
        ArrayList<AdDTO> resultList = new ArrayList<AdDTO>();
        for(Ad ad : adList)
        {
            resultList.add(toDTO(ad));
        }

        return resultList;
    }
    ///
    private AdDTO toDTO(Ad ad)
    {
        if(ad == null)
        {
            return null;
        }
        return new AdDTO(ad.getAdId(), ad.getUser().getId(), toCategoryDTO(ad.getCategory()), ad.getTitle(), ad.getDescription(), ad.getDuration());
    }

    private Ad toAd(AdDTO adDTO)
    {
        if(adDTO == null)
        {
            return null;
        }

        User adOwner = userService.findUserById(adDTO.getUser());

        return new Ad(adOwner, toCategory(adDTO.getCategory()), adDTO.getTitle(), adDTO.getDescription(), adDTO.getDuration(), null);
    }

    private Category toCategory(CategoryDTO categoryDTO)
    {
        if(categoryDTO == null)
        {
            return null;
        }
        return new Category(categoryDTO.getCategoryId(), categoryDTO.getName(), null);
    }

    private CategoryDTO toCategoryDTO(Category category)
    {
        if(category == null)
        {
            return null;
        }

        return new CategoryDTO(category.getCategoryId(), category.getName());
    }


}






