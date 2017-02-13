package com.example.repositories;

import com.example.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Teddy on 2017-02-13.
 */
public interface PhotoRepository extends JpaRepository<Photo, Long>{
    Photo findById(long id);
}
