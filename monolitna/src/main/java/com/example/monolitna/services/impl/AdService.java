package com.example.monolitna.services.impl;

import com.example.monolitna.dto.request.CreateAdRequest;
import com.example.monolitna.dto.request.UpdateAdRequest;
import com.example.monolitna.dto.response.*;
import com.example.monolitna.entity.*;
import com.example.monolitna.repository.*;
import com.example.monolitna.services.IAdService;
import com.example.monolitna.services.ISimpleUserService;
import com.example.monolitna.util.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class AdService implements IAdService {

    private final IPictureRepository _pictureRepository;
    private final IAdRepository _adRepository;
    private final SimpleUserService _simpleUserService;
    private final ICarModelRepository _carModelRepository;
    private final IFuelTypeRepository _fuelTypeRepository;
    private final IGearshiftTypeRepository _gearshiftTypeRepository;
    private final ICarRepository _carRepository;
    private final CarService _carService;
    private final AgentService _agentService;
    private final PricelistService _pricelistService;

    public AdService(IPictureRepository pictureRepository, IAdRepository adRepository, ISimpleUserRepository simpleUserRepository, ISimpleUserService simpleUserService, SimpleUserService simpleUserService1, ICarModelRepository carModelRepository, IFuelTypeRepository fuelTypeRepository, IGearshiftTypeRepository gearshiftTypeRepository, ICarRepository carRepository, CarService carService, AgentService agentService, PricelistService pricelistService) {
        _pictureRepository = pictureRepository;
        _adRepository = adRepository;
        _simpleUserService = simpleUserService1;
        _carModelRepository = carModelRepository;
        _fuelTypeRepository = fuelTypeRepository;
        _gearshiftTypeRepository = gearshiftTypeRepository;
        _carRepository = carRepository;
        _carService = carService;
        _agentService = agentService;
        _pricelistService = pricelistService;
    }

    @Override
    public PictureResponse getPicture(Long adId) {
        Ad ad = _adRepository.findOneById(adId);
        Picture picture = ad.getAdPictures().iterator().next();
        Picture decompressedPicture = new Picture(picture.getName(), picture.getType(), decompressBytes(picture.getPicByte()), ad);
        return mapToPictureResponse(decompressedPicture);
    }

    private PictureResponse mapToPictureResponse(Picture decompressedPicture) {
        PictureResponse pictureResponse = new PictureResponse();
        pictureResponse.setId(decompressedPicture.getId());
        pictureResponse.setName(decompressedPicture.getName());
        pictureResponse.setType(decompressedPicture.getType());
        pictureResponse.setPicByte(decompressedPicture.getPicByte());
        return pictureResponse;
    }

    @Override
    public List<PictureResponse> getAllPictures(Long adID){
        List<PictureResponse> pictureResponses = new ArrayList<>();
        Ad ad = _adRepository.findOneById(adID);
        List<Picture> pictures = ad.getAdPictures();
        for(Picture picture : pictures){
            Picture decompressedPicture = new Picture(picture.getName(), picture.getType(), decompressBytes(picture.getPicByte()), ad);
            pictureResponses.add(mapToPictureResponse(decompressedPicture));
        }
        return pictureResponses;
    }

    @Override
    public void uploadPicture(MultipartFile file) throws IOException {
        Picture img = new Picture(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        _pictureRepository.save(img);
    }

    @Override
    public PictureResponse getImage(Long id) {
        Picture retrievedImage = _pictureRepository.findOneById(id);
        Picture img = new Picture(retrievedImage.getName(), retrievedImage.getType(),
                decompressBytes(retrievedImage.getPicByte()));
        return mapToPictureResponse(img);
    }

    @Override
    public SearchResponse search(String address, String fromDateString, String toDateString, String fromTimeString, String toTimeString, Long carBrandId, Long carModelId, Long carClassId, Long fuelTypeId, Long gearshiftTypeId, int minPrice, int maxPrice, int limitedKm, int kmTraveled, int seats, boolean availableCDW) {
        if(!fromDateString.equals("")){
            LocalDate fromDate = LocalDate.parse(fromDateString);
            System.out.println("From date " + fromDate);
        }
        if(!toDateString.equals("")){
            LocalDate toDate = LocalDate.parse(toDateString);
            System.out.println("To date " + toDate);
        }
        if(!fromTimeString.equals("")){
            LocalTime fromTime = LocalTime.parse(fromTimeString);
            System.out.println("From time " + fromTime);
        }
        if(!toTimeString.equals("")){
            LocalTime toTime = LocalTime.parse(toTimeString);
            System.out.println("To time " + toTime);
        }

        List<Ad> filteredAds = filteredAds(address, carBrandId,carModelId,fuelTypeId,gearshiftTypeId,carClassId, minPrice,maxPrice,
                kmTraveled,limitedKm,availableCDW,seats);
        List<AdResponse> adResponses =  mapAdsToAdResponses(filteredAds);
        return mapToSearchResponse(adResponses);
    }

    private SearchResponse mapToSearchResponse(List<AdResponse> adResponses) {
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setAds(adResponses);
        return searchResponse;
    }

    private List<AdResponse> mapAdsToAdResponses(List<Ad> ads){
        List<AdResponse> adResponses = new ArrayList<>();
        for(Ad ad: ads){
            AdResponse response = mapAdToAdResponse(ad);
            adResponses.add(response);
        }
        return adResponses;
    }

    private List<Ad> filteredAds(String address, Long carBrandId, Long carModelId, Long fuelTypeId,Long gearshiftTypeId,Long carClassId,
                                 int minPrice, int maxPrice,int kmTraveled, int limitedKm, boolean availableCDW, int seats) {
        List<Ad> allAds = _adRepository.findAll();
        return allAds
                .stream()
                .filter(ad -> {
                    if(ad.isSimpleUser()){
                        SimpleUserResponse simpleUser = _simpleUserService.getSimpleUserById(ad.getPublisherId());
                        return simpleUser.getAddress().contains(address);
                    }else {
                        AgentResponse agent = _agentService.getAgentById(ad.getPublisherId());
                        return agent.getAddress().contains(address);
                    }
                })
                .filter(ad -> {
                    if(carBrandId != -1) {
                        return ad.getCar().getCarModel().getCarBrand().getId().equals(carBrandId);
                    } else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(carModelId != -1){
                        return ad.getCar().getCarModel().getId().equals(carModelId);
                    }else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(fuelTypeId != -1){
                        return ad.getCar().getFuelType().getId().equals(fuelTypeId);
                    }else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(gearshiftTypeId != -1){
                        return ad.getCar().getGearshiftType().getId().equals(gearshiftTypeId);
                    }else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(carClassId != -1){
                        return ad.getCar().getCarModel().getCarClass().getId().equals(carClassId);
                    }else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(minPrice != -1){
                        Pricelist pricelist = ad.getPricelist();
                        return minPrice <= pricelist.getPricePerDay();
                    }else{
                        return true;
                    }
                })
                .filter(ad -> {
                    if(maxPrice != -1){
                        Pricelist pricelist = ad.getPricelist();
                        return maxPrice >= pricelist.getPricePerDay();
                    }else{
                        return true;
                    }
                })
                .filter(ad -> {
                    if(limitedKm != -1){
                        return ad.getLimitedKm() <= limitedKm;
                    }else{
                        return true;
                    }
                })
                .filter(ad -> {
                    if(kmTraveled != -1){
                        return ad.getCar().getKmTraveled() <= kmTraveled;
                    }else{
                        return true;
                    }
                })
                .filter(ad -> {
                    if(limitedKm != -1){
                        return ad.getLimitedKm() <= limitedKm;
                    }else{
                        return true;
                    }
                })
                .filter(ad -> ad.isCdw() == availableCDW)
                .filter(ad -> {
                    if(seats != -1){
                        return ad.getSeats() == seats;
                    }else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }


    @Override
    public AdResponse getAdById(Long id) {
        Ad ad = _adRepository.findOneById(id);
        if(ad != null) {
            return mapAdToAdResponse(ad);
        }
        return null;
    }

    @Override
    public boolean deleteAdById(Long id) {
        Ad ad = _adRepository.findOneById(id);
        if(ad != null){
            Car car = _carRepository.findOneById(ad.getCar().getId());
            _carRepository.deleteById(car.getId());
            return true;
        }
        return false;
    }

    @Override
    public AdResponse createAd(List<MultipartFile> fileList, CreateAdRequest request)  throws GeneralException, IOException{
        if(request.isSimpleUser()) {
            SimpleUserResponse simpleUserResponse = _simpleUserService.getSimpleUserById(request.getPublisherId());
            if (simpleUserResponse.getNumOfAds() >= 3) {
                throw new GeneralException("You can not create more than 3 advertisements.", HttpStatus.BAD_REQUEST);
            }
            _simpleUserService.increase(simpleUserResponse.getId());
        }

        Car car = new Car();
        CarModel carModel = _carModelRepository.findOneById(request.getCarModelId());
        car.setCarModel(carModel);
        car.setKmTraveled(request.getKmTraveled());
        car.setFuelType(_fuelTypeRepository.findOneById(request.getFuelTypeId()));
        car.setGearshiftType(_gearshiftTypeRepository.findOneById(request.getGearshiftTypeId()));
        _carRepository.save(car);

        Ad ad = new Ad();
        ad.setName(carModel.getCarBrand().getName() + " " + carModel.getName() + " " + carModel.getCarClass().getName());
        ad.setCar(car);
        ad.setCdw(request.isCdw());
        ad.setCreationDate(request.getCreationDate());
        ad.setLimitedDistance(request.isLimitedDistance());
        ad.setLimitedKm(request.getLimitedKm());
        ad.setSeats(request.getSeats());
        ad.setPublisherId(request.getPublisherId());
        ad.setSimpleUser(request.isSimpleUser());
        List<Picture> pictures = new ArrayList<>();
        for (MultipartFile file : fileList) {
            Picture picture = new Picture();
            picture.setAd(ad);
            picture.setName(file.getOriginalFilename());
            picture.setType(file.getContentType());
            picture.setPicByte(compressBytes(file.getBytes()));
            pictures.add(picture);
            _pictureRepository.save(picture);
        }
        ad.setAdPictures(pictures);
        _adRepository.save(ad);
        return mapAdToAdResponse(ad);
    }

    @Override
    public List<AdResponse> getAllAds() {
        List<Ad> ads = _adRepository.findAll();

        return  ads.stream()
                .map(ad -> mapAdToAdResponse(ad))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateAdById(Long id, UpdateAdRequest request) {
        Ad ad = _adRepository.findOneById(id);
        if(ad != null) {
            ad.setLimitedKm(request.getLimitedKm());
            ad.setSeats(request.getSeats());
            ad.setLimitedDistance(request.isLimitedDistance());
            ad.setCdw(request.isCdw());

            _adRepository.save(ad);
            return true;
        }
        return false;
    }

    @Override
    public List<AdResponse> getAllPublisherAds(Long id, boolean request) {
        List<Ad> ads = _adRepository.findAllByPublisherId(id);
        List<Ad> filteredAds = new ArrayList<>();
        for(Ad a: ads) {
            if(a.isSimpleUser() == request){
                filteredAds.add(a);
            }
        }
        return  filteredAds.stream()
                .map(ad -> mapAdToAdResponse(ad))
                .collect(Collectors.toList());
    }

    public AdResponse mapAdToAdResponse(Ad ad) {
        AdResponse adResponse = new AdResponse();
        adResponse.setId(ad.getId());
        adResponse.setCar(_carService.mapCarToResponse(ad.getCar()));
        adResponse.setCreationDate(ad.getCreationDate());
        adResponse.setName(ad.getName());
        adResponse.setLimitedKm(ad.getLimitedKm());
        adResponse.setSeats(ad.getSeats());
        adResponse.setCdw(ad.isCdw());
        adResponse.setLimitedDistance(ad.isLimitedDistance());
        adResponse.setSimpleUser(ad.isSimpleUser());
        PublisherResponse publisherResponse = new PublisherResponse();

        PricelistResponse pricelistResponse = _pricelistService.getPricelistById(ad.getPricelist().getId());
        adResponse.setPricelist(pricelistResponse);


        if(ad.isSimpleUser()){
            SimpleUserResponse simpleUser = _simpleUserService.getSimpleUserById(ad.getPublisherId());
            publisherResponse.setId(simpleUser.getId());
            publisherResponse.setAddress(simpleUser.getAddress());
            publisherResponse.setFirstName(simpleUser.getFirstName());
            publisherResponse.setLastName(simpleUser.getLastName());
            publisherResponse.setNumOfAds(simpleUser.getNumOfAds());
            publisherResponse.setSsn(simpleUser.getSsn());
            publisherResponse.setUsername(simpleUser.getUsername());
        }else{
            System.out.println(ad.getPublisherId());
            AgentResponse agentResponse = _agentService.getAgentById(ad.getPublisherId());
            System.out.println(agentResponse);
            publisherResponse.setAddress(agentResponse.getAddress());
            publisherResponse.setName(agentResponse.getName());
            publisherResponse.setDateFounded(agentResponse.getDateFounded());
            publisherResponse.setId(agentResponse.getId());
            publisherResponse.setBankAccountNumber(agentResponse.getBankAccountNumber());
        }
        adResponse.setPublisher(publisherResponse);
        if(ad.getAdPictures() != null){
            List<PictureResponse> pictures = new ArrayList<>();
            for(Picture pic: ad.getAdPictures()){
                PictureResponse pictureResponse = mapToPictureResponse(pic);
                pictures.add(pictureResponse);
            }
            adResponse.setPictures(pictures);
        }
        return adResponse;
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

    public Ad mapAdResponseToAd(AdResponse adResponse) {
        Ad ad = new Ad();
        ad.setId(adResponse.getId());;
        ad.setCar(ad.getCar());
        ad.setCreationDate(adResponse.getCreationDate());
        ad.setName(adResponse.getName());
        ad.setLimitedKm(adResponse.getLimitedKm());
        ad.setSeats(adResponse.getSeats());
        ad.setCdw(adResponse.isCdw());
        ad.setLimitedDistance(adResponse.isLimitedDistance());
        ad.setSimpleUser(adResponse.isSimpleUser());
        ad.setPublisherId(adResponse.getPublisher().getId());
        if(adResponse.getPictures() != null){
            List<Picture> pictures = new ArrayList<>();
            for(PictureResponse pic: adResponse.getPictures()){
                Picture picture = mapPictureResponseToPicture(pic);
                pictures.add(picture);
            }
            ad.setAdPictures(pictures);
        }

        return ad;
    }

    private Picture mapPictureResponseToPicture(PictureResponse decompressedPicture) {
        Picture picture = new Picture();
        picture.setId(decompressedPicture.getId());
        picture.setName(decompressedPicture.getName());
        picture.setType(decompressedPicture.getType());
        picture.setPicByte(decompressedPicture.getPicByte());
        return picture;
    }
}
