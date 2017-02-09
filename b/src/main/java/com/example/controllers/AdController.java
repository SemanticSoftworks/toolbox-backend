package com.example.controllers;

import com.example.domain.Ad;
import com.example.domain.Category;
import com.example.domain.User;
import com.example.model.AdDTO;
import com.example.model.CategoryDTO;
import com.example.service.AdService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AdDTO> getAd(@PathVariable Long id){

        AdDTO ad = toDTO(adService.findAdById(id));
        if(ad == null){

            return new ResponseEntity<>(ad, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ad, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ResponseEntity<AdDTO> addAd(/*@RequestBody AdDTO adDTO*/) //
    {
        AdDTO test = new AdDTO(-1,1, new CategoryDTO(new Long(-1),"test"), "testAdd"+50, "testDescription"+50, Calendar.getInstance());
        for(int i=0; i<50;i++)
        {
            adService.addAd(toAd(new AdDTO(-1,1, new CategoryDTO(new Long(1),"test"), "testAdd"+i, "testDescription"+i, Calendar.getInstance())));
        }
        //AdDTO addedAdDTO = toDTO(adService.addAd(toAd(adDTO)));
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @RequestMapping(value = "/getads/{pageNr}", method = RequestMethod.GET)
    public ResponseEntity<List<AdDTO>> getAllAds(@PathVariable int pageNr)
    {
        List<AdDTO> resultList = convertToAdDTO(adService.getAllAds(pageNr).getContent());
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    private List<AdDTO> convertToAdDTO(List<Ad> adList)
    {
        ArrayList<AdDTO> resultList = new ArrayList<AdDTO>();
        for(Ad ad : adList)
        {
            resultList.add(toDTO(ad));
        }

        return resultList;
    }

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
        return new Ad(adOwner,toCategory(adDTO.getCategory()), adDTO.getTitle(), adDTO.getDescription(), adDTO.getDuration());
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






