package com.example.monolitna.controller;

import com.example.monolitna.dto.request.CreateCommentRequest;
import com.example.monolitna.dto.request.RequestId;
import com.example.monolitna.dto.response.CommentResponse;
import com.example.monolitna.dto.response.TextResponse;
import com.example.monolitna.services.ICommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final ICommentService _commentService;

    public CommentController(ICommentService commentService) {
        _commentService = commentService;
    }

    @GetMapping("/ad/{id}")
    public List<CommentResponse> getAllCommentsByAd(@PathVariable("id") Long id){
        return _commentService.getAllCommentsByAd(id);
    }

    @GetMapping("/status")
    public ResponseEntity<?> getAllCommentsByStatus(@PathParam("status") String status){
        System.out.println(status);
        List<CommentResponse> commentResponses = _commentService.getAllCommentsByStatus(status);
        if(commentResponses.isEmpty()){
            TextResponse textResponse = new TextResponse();
            textResponse.setText("There is no comments.");
            return new ResponseEntity<>(textResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commentResponses, HttpStatus.OK);
    }

    @PostMapping()
    public CommentResponse createComment(@RequestBody CreateCommentRequest request){
        return _commentService.createComment(request);
    }

    @PutMapping("/approve")
    public CommentResponse approveComment(@RequestBody RequestId request){
        return _commentService.approveComment(request.getId());
    }

    @PutMapping("/deny")
    public CommentResponse denyComment(@RequestBody RequestId request){
        return _commentService.denyComment(request.getId());
    }
}
