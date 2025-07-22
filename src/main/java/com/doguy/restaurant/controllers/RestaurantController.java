package com.doguy.restaurant.controllers;

import com.doguy.restaurant.domain.dto.RestaurantCreateUpdateRequestDto;
import com.doguy.restaurant.domain.dto.RestaurantDto;
import com.doguy.restaurant.domain.entities.Restaurant;
import com.doguy.restaurant.domain.entities.RestaurantCreateUpdateRequest;
import com.doguy.restaurant.mappers.RestaurantMapper;
import com.doguy.restaurant.services.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant( @Valid @RequestBody RestaurantCreateUpdateRequestDto request ) {
        RestaurantCreateUpdateRequest restaurantCreateUpdateRequest = restaurantMapper
                .toRestaurantCreateUpdateRequest(request);

        Restaurant restaurant = restaurantService
                .createRestaurant(restaurantCreateUpdateRequest);

        RestaurantDto createdRestaurantDto = restaurantMapper
                .toRestaurantDto(restaurant);

        return ResponseEntity
                .ok(createdRestaurantDto);

    }


}
