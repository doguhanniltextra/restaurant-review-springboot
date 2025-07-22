package com.doguy.restaurant.controllers;

import com.doguy.restaurant.domain.dto.RestaurantCreateUpdateRequestDto;
import com.doguy.restaurant.domain.dto.RestaurantDto;
import com.doguy.restaurant.domain.dto.RestaurantSummaryDto;
import com.doguy.restaurant.domain.entities.Restaurant;
import com.doguy.restaurant.domain.entities.RestaurantCreateUpdateRequest;
import com.doguy.restaurant.mappers.RestaurantMapper;
import com.doguy.restaurant.services.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Page<RestaurantSummaryDto> searchRestaurants(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Float minRating,
            @RequestParam(required = false) Float latitude,
            @RequestParam(required = false) Float longitude,
            @RequestParam(required = false) Float radius,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue= "20") int size

    ) {
        Page<Restaurant> searchResults = restaurantService.searchRestaurant(
                q, minRating, latitude,
                longitude, radius, PageRequest.of(page - 1, size)
        );

    return searchResults.map(restaurantMapper:: toSummaryDto);
    }


    @GetMapping(path = "/{restaurant_id}")
    public ResponseEntity<RestaurantDto> getRestaurant(@PathVariable("restaurant_id") String  restaurant_id){
        return restaurantService.getRestaurant(restaurant_id)
                .map(restaurant -> ResponseEntity.ok(restaurantMapper.toRestaurantDto(restaurant)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{restaurant_id")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("restaurant_id") String  restaurant_id){
         restaurantService.deleteRestaurant(restaurant_id);
         return ResponseEntity.noContent().build();
    }
}
