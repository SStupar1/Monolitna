package com.example.monolitna.dto.response;

import com.example.monolitna.entity.Pricelist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdResponse {

    private Long id;

    private String name;

    private boolean limitedDistance;

    private int limitedKm;

    private boolean cdw;

    private int seats;

    private LocalDate creationDate;

    private CarResponse car;

    private boolean simpleUser;

    private PublisherResponse publisher;

    private List<PictureResponse> pictures;

    private PricelistResponse pricelist;

}
