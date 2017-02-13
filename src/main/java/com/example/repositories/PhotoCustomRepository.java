package com.example.repositories;

import com.example.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Teddy on 2017-02-13.
 */
@Repository
public interface PhotoCustomRepository {

    List<Photo> getPhotosByAdId(long adId);
}
