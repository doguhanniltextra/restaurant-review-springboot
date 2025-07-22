package com.doguy.restaurant.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantCreateUpdateRequest {
    private String name;
    private String cuisineType;
    private String contentInformation;
    private Address address;
    private OperatingHours operatingHours;
    private List<String> photoIds;
}
