package com.example.controllers;

import com.example.domain.Ad;
import com.example.domain.Photo;
import com.example.model.AdDTO;
import com.example.model.PhotoDTO;
import com.example.service.AdService;
import com.example.service.PhotoService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teddy on 2017-02-13.
 */
@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private AdService adService;
    @Autowired
    private PhotoService photoService;

    //photoDTO:n should have ad_id.
    @RequestMapping(value = "/addphoto", method = RequestMethod.POST, consumes={"application/json"})
    public ResponseEntity<PhotoDTO> addPhoto(@RequestBody PhotoDTO photo)
    {
        Ad ad = adService.findAdById(photo.getAdId());
        if(ad ==null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ad.getPhotos().add(toPhoto(photo));
        adService.addAd(ad);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    @RequestMapping(value = "/getadphotos/{adId}", method = RequestMethod.GET)
    public ResponseEntity<List<PhotoDTO>> getImgsOfAd(@PathVariable int adId)
    {
        List<PhotoDTO> resultList = convertToPhotoDTOList(photoService.getPhotosByAdId(adId));
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    private List<PhotoDTO> convertToPhotoDTOList(List<Photo> photoList)
    {
        ArrayList<PhotoDTO> resultList = new ArrayList<>();
        for(Photo photo : photoList)
        {
            resultList.add(toPhotoDTO(photo));
        }
        return resultList;
    }

    private Photo toPhoto(PhotoDTO photo)
    {
        return new Photo(-1, photo.getImgByte(), adService.findAdById(photo.getAdId()));
    }

    private PhotoDTO toPhotoDTO(Photo photo)
    {
        return new PhotoDTO(photo.getId(), photo.getPhotobyte(), photo.getAd().getAdId());
    }

}
