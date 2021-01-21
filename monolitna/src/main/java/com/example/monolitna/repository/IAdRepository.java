package com.example.monolitna.repository;

import com.example.monolitna.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAdRepository extends JpaRepository<Ad, Long> {
    Ad findOneById(Long adId);

    List<Ad> findAllByPublisherId(Long id);
}
