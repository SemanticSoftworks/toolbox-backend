package com.example.controllers;

import com.example.domain.Ad;
import com.example.domain.User;
import com.example.model.AdDTO;
import com.example.service.AdService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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
            System.out.println("ad is null!");
            return new ResponseEntity<>(ad, HttpStatus.BAD_REQUEST);
        }
        System.out.println("ad is okey!");
        return new ResponseEntity<>(ad, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<AdDTO> addAd(@RequestBody AdDTO adDTO) //
    {
        //AdDTO test = new AdDTO(-1,1, new Category(1,"test", new ArrayList()), "testAdd", "testDescription", Calendar.getInstance());
        AdDTO addedAdDTO = toDTO(adService.addAd(toAd(adDTO)));
        return new ResponseEntity<>(addedAdDTO, HttpStatus.OK);
    }

    private AdDTO toDTO(Ad ad)
    {
        if(ad == null)
        {
            return null;
        }
        return new AdDTO(ad.getAdId(), ad.getUser().getId(), ad.getCategory(), ad.getTitle(), ad.getDescription(), ad.getDuration());
    }

    private Ad toAd(AdDTO adDTO)
    {
        if(adDTO == null)
        {
            return null;
        }

        User adOwner = userService.findUserById(adDTO.getUser());
        return new Ad(adOwner,adDTO.getCategory(), adDTO.getTitle(), adDTO.getDescription(), adDTO.getDuration());
    }

}






