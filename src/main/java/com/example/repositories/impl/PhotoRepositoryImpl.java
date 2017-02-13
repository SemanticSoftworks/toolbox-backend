package com.example.repositories.impl;

import com.example.domain.Photo;
import com.example.repositories.PhotoCustomRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Teddy on 2017-02-13.
 */
public class PhotoRepositoryImpl implements PhotoCustomRepository {

    @Autowired
    private SessionFactory factory;

    @Override
    public List<Photo> getPhotosByAdId(long adId) {


        Session session = factory.getCurrentSession();
        //from DB_Message msg where msg.id = :searchId
        Query q = session.createQuery("select photo from com.example.domain.Photo photo where photo.ad.adId =:id");
        q.setParameter("id", adId);
        List<Photo> resultList = q.list();

        return resultList;
    }
}
