package com.example.monolitna.repository;


import com.example.monolitna.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPictureRepository extends JpaRepository<Picture, Long> {
    Picture findOneById(Long id);
}