package com.example.repositories;

import com.example.domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by Teddy on 2017-02-08.
 */

public interface AdRepository extends JpaRepository<Ad, Long> {
    Ad findByUser(long userId);
    Ad findByAdId(long id);
}
