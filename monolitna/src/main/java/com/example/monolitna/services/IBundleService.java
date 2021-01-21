package com.example.monolitna.services;

import com.example.monolitna.dto.request.ReservationRequest;
import com.example.monolitna.dto.response.BundleResponse;

import java.util.List;

public interface IBundleService {
    boolean createBundle(List<ReservationRequest> request);

    List<BundleResponse> getPublisherBundles(Long publisherId, boolean simpleUser);

    BundleResponse approveBundle(Long id);

    BundleResponse denyBundle(Long id);
}