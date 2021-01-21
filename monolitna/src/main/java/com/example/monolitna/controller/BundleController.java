package com.example.monolitna.controller;

import com.example.monolitna.dto.request.RequestId;
import com.example.monolitna.dto.request.ReservationListRequest;
import com.example.monolitna.dto.response.BundleResponse;
import com.example.monolitna.dto.response.TextResponse;
import com.example.monolitna.services.IBundleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bundles")
public class BundleController {

    private final IBundleService _bundleService;

    public BundleController(IBundleService bundleService) {
        _bundleService = bundleService;
    }

    @GetMapping("/publisher")
    public ResponseEntity<?> getPublisherBundles(@RequestParam("publisherId") Long publisherId, @RequestParam("simpleUser") boolean simpleUser){
        List<BundleResponse> retVal = _bundleService.getPublisherBundles(publisherId, simpleUser);
        if(retVal.isEmpty()){
            TextResponse stringResponse = new TextResponse();
            stringResponse.setText("There is no bundles.");
            return new ResponseEntity<>(stringResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createBundle(@RequestBody ReservationListRequest request){
        TextResponse response = new TextResponse();
        if(_bundleService.createBundle(request.getReservationRequestList())){
            response.setText("created bundle");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.setText("error creating bundle");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/approve")
    public ResponseEntity<?> approveBundle(@RequestBody RequestId request){
        return new ResponseEntity<>(_bundleService.approveBundle(request.getId()), HttpStatus.OK);
    }

    @PutMapping("/deny")
    public ResponseEntity<?> denyBundle(@RequestBody RequestId request){
        return new ResponseEntity<>(_bundleService.denyBundle(request.getId()), HttpStatus.OK);
    }
}