package com.example.monolitna.services.impl;

import com.example.monolitna.dto.request.ReservationRequest;
import com.example.monolitna.dto.response.AdResponse;
import com.example.monolitna.dto.response.ReservationResponse;
import com.example.monolitna.entity.Ad;
import com.example.monolitna.entity.Reservation;
import com.example.monolitna.repository.IBundleRepository;
import com.example.monolitna.repository.IReservationRepository;
import com.example.monolitna.services.IReservationService;
import com.example.monolitna.util.enums.ReservationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService implements IReservationService {

    private final IReservationRepository _reservationRepository;

    private final AdService _adService;

    private final IBundleRepository _bundleRepository;


    public ReservationService(IReservationRepository reservationRepository, AdService adService, IBundleRepository bundleRepository) {
        _reservationRepository = reservationRepository;
        _adService = adService;
        _bundleRepository = bundleRepository;
    }

    @Override
    public List<ReservationResponse> getAllAdReservations(Long id) {
        List<Reservation> reservations = new ArrayList<>();
        List<Reservation> allReservations = _reservationRepository.findAll();
        for(Reservation reservation: allReservations){
            if(reservation.getBundle() == null) {
                if (reservation.getAd().getId().equals(id)) {
                    if (reservation.getStatus().equals(ReservationStatus.PENDING)) {
                        reservations.add(reservation);
                    }
                }
            }
        }
        return mapReservationsToReservationResponses(reservations);
    }

    //pending rezervacije RADI TOP
    @Override
    public List<ReservationResponse> getAllPublisherReservations(Long publisherId, boolean simpleUser) {
        List<Reservation> reservations = new ArrayList<>();
        List<Reservation> allReservations = _reservationRepository.findAllByStatus(ReservationStatus.PENDING);
        for(Reservation reservation: allReservations){
            //ako rezervacija ne pripada bundleu
            if(reservation.getBundle() == null) {
                Ad ad = reservation.getAd();
                //provera publisher-a
                if (ad.getPublisherId() == publisherId) {
                    if (simpleUser == ad.isSimpleUser()) {
                        reservations.add(reservation);
                    }
                }
            }
        }
        return mapReservationsToReservationResponses(reservations);
    }

    @Override
    public ReservationResponse getReservation(Long id) {
        Reservation reservation = _reservationRepository.findOneById(id);
        if(reservation != null) {
            return mapReservationToReservationResponse(reservation);
        }
        return null;
    }

    //vraca sve pojedinacne rezervacije od jednog customer-a RADI TOP
    @Override
    public List<ReservationResponse> getAllCustomerReservations(Long customerId, boolean simpleUser) {
        List<Reservation> reservations = new ArrayList<>();
        List<Reservation> allReservations = _reservationRepository.findAll();
        for(Reservation reservation: allReservations){
            if(reservation.getBundle() == null) {
                if (reservation.getCustomerId() == customerId) {
                    if (simpleUser == reservation.isSimpleUser()) {
                        reservations.add(reservation);
                    }
                }
            }
        }
        return mapReservationsToReservationResponses(reservations);
    }

    @Override
    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        Reservation reservation = createReservationEntity(reservationRequest);
        _reservationRepository.save(reservation);
        return mapReservationToReservationResponse(reservation);
    }

    @Override
    public ReservationResponse approveReservation(Long id) {
        Reservation fixReservation = _reservationRepository.findOneById(id);
        LocalDate fromFixDate = fixReservation.getFromDate();
        LocalDate toFixDate = fixReservation.getToDate();
        LocalTime fromFixTime = fixReservation.getFromTime();
        LocalTime toFixTime = fixReservation.getToTime();
        fixReservation.setStatus(ReservationStatus.APPROVED);
        _reservationRepository.save(fixReservation);

        List<Reservation> allReservations = _reservationRepository.findAllByStatus(ReservationStatus.PENDING);
        for(Reservation checkReservation: allReservations){
            LocalDate fromCheckDate = checkReservation.getFromDate();
            LocalDate toCheckDate = checkReservation.getToDate();
            LocalTime fromCheckTime = checkReservation.getFromTime();
            LocalTime toCheckTime = checkReservation.getToTime();
            if(overlapDate(fromFixDate, toFixDate, fromFixTime, toFixTime, fromCheckDate, toCheckDate, fromCheckTime, toCheckTime)){
                //ako se datumi preklapaju
                checkReservation.setStatus(ReservationStatus.DENIED);
                _reservationRepository.save(checkReservation);
            }
        }

        return mapReservationToReservationResponse(fixReservation);
    }

    private boolean overlapDate(LocalDate fromFixDate, LocalDate toFixDate, LocalTime fromFixTime, LocalTime toFixTime,
                                LocalDate fromCheckDate, LocalDate toCheckDate, LocalTime fromCheckTime, LocalTime toCheckTime){
        boolean retVal = true;

        if(fromCheckDate.isBefore(fromFixDate)){
            if(toCheckDate.isBefore(fromFixDate)){
                retVal = false;
            }else if(toCheckDate.isEqual(fromFixDate)){
                //proveri vreme
                if(toCheckTime.isBefore(fromFixTime)){
                    retVal = false;
                }
            }
        }
        if(fromCheckDate.isAfter(toFixDate)){
            retVal = false;
        }
        if(fromCheckDate.isEqual(toFixDate)){
            //proveri vreme
            if(fromCheckTime.isBefore(toFixTime)){
                retVal = false;
            }
        }
        return retVal;
    }

    @Override
    public ReservationResponse denyReservation(Long id) {
        Reservation reservation = _reservationRepository.findOneById(id);
        reservation.setStatus(ReservationStatus.DENIED);
        Reservation savedReservation = _reservationRepository.save(reservation);
        return mapReservationToReservationResponse(savedReservation);
    }

    public List<ReservationResponse> mapReservationsToReservationResponses(List<Reservation> reservations){
        List<ReservationResponse> reservationResponses = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationResponse reservationResponse = mapReservationToReservationResponse(reservation);
            reservationResponses.add(reservationResponse);
        }
        return reservationResponses;
    }

    public Reservation createReservationEntity(ReservationRequest reservationRequest) {
        LocalDate fromDate = LocalDate.parse(reservationRequest.getFromDateString());
        LocalTime fromTime = LocalTime.parse(reservationRequest.getFromTimeString());
        LocalDate toDate = LocalDate.parse(reservationRequest.getToDateString());
        LocalTime toTime = LocalTime.parse(reservationRequest.getToTimeString());
        Reservation reservation = new Reservation();
        reservation.setCustomerId(reservationRequest.getCustomerId());
        AdResponse  adResponse = _adService.getAdById(reservationRequest.getAdId());
        reservation.setAd(_adService.mapAdResponseToAd(adResponse));
        reservation.setFromDate(fromDate);
        reservation.setToDate(toDate);
        reservation.setFromTime(fromTime);
        reservation.setToTime(toTime);
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setSimpleUser(reservationRequest.isSimpleUser());
        return reservation;
    }

    public ReservationResponse mapReservationToReservationResponse(Reservation reservation) {
        ReservationResponse reservationResponse = new ReservationResponse();
        Ad ad =  reservation.getAd();
        reservationResponse.setCustomerId(reservation.getCustomerId());
        reservationResponse.setToDate(reservation.getToDate());
        reservationResponse.setFromDate(reservation.getFromDate());
        reservationResponse.setId(reservation.getId());
        reservationResponse.setFromTime(reservation.getFromTime());
        reservationResponse.setToTime(reservation.getToTime());
        reservationResponse.setAd(ad);
        reservationResponse.setSimpleUser(reservation.isSimpleUser());
        reservationResponse.setStatus(reservation.getStatus());
        return reservationResponse;
    }


}