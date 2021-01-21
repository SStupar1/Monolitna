package com.example.monolitna.services;

import com.example.monolitna.dto.request.CreateAdRequest;
import com.example.monolitna.dto.request.UpdateAdRequest;
import com.example.monolitna.dto.response.AdResponse;
import com.example.monolitna.dto.response.PictureResponse;
import com.example.monolitna.dto.response.SearchResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IAdService {
    AdResponse getAdById(Long id);

    boolean deleteAdById(Long id);

    AdResponse createAd(List<MultipartFile> fileList, CreateAdRequest request) throws Exception;

    List<AdResponse> getAllAds();

    boolean updateAdById(Long id, UpdateAdRequest request);

    List<AdResponse> getAllPublisherAds(Long id, boolean request);

    PictureResponse getPicture(Long adId);

    List<PictureResponse> getAllPictures(Long adID);

    void uploadPicture(MultipartFile file) throws IOException;

    PictureResponse getImage(Long id);

    SearchResponse search(String address, String fromDateString, String toDateString, String fromTimeString, String toTimeString, Long carBrandId,
                          Long carModelId, Long carClassId, Long fuelTypeId, Long gearshiftTypeId, int minPrice, int maxPrice,
                          int limitedKm, int kmTraveled, int seats, boolean availableCDW);}
