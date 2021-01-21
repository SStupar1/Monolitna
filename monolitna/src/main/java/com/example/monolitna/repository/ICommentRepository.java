package com.example.monolitna.repository;

import com.example.monolitna.entity.Comment;
import com.example.monolitna.util.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByAd_Id(Long id);

    Comment findOneById(Long id);

    List<Comment> findAllByStatus(RequestStatus requestStatus);
}
