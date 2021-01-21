package com.example.monolitna.services;

import com.example.monolitna.dto.request.CreateCommentRequest;
import com.example.monolitna.dto.response.CommentResponse;

import java.util.List;

public interface ICommentService {
    List<CommentResponse> getAllCommentsByAd(Long id);

    CommentResponse createComment(CreateCommentRequest request);

    CommentResponse approveComment(Long id);

    CommentResponse denyComment(Long id);

    List<CommentResponse> getAllCommentsByStatus(String status);
}

