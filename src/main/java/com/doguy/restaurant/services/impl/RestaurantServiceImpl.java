package com.doguy.restaurant.services.impl;


import com.doguy.restaurant.domain.entities.*;
import com.doguy.restaurant.exceptions.RestaurantNotFoundException;
import com.doguy.restaurant.repositories.RestaurantRepository;
import com.doguy.restaurant.services.GeoLocationService;
import com.doguy.restaurant.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final GeoLocationService geoLocationService;

    @Override
    public Restaurant createRestaurant(RestaurantCreateUpdateRequest request) {
        Address address = request.getAddress();
        GeoLocation geoLocation = geoLocationService.geoLocate(address);
        GeoPoint geoPoint = new GeoPoint(geoLocation.getLatitude(), geoLocation.getLongitude());


        List<String> photoIds = request.getPhotoIds();
        List<Photo> photos = photoIds.stream().map(photoUrl -> Photo.builder()
                .url(photoUrl)
                .uploadDate(LocalDateTime.now())
                .build()).toList();

        Restaurant build = Restaurant.builder()
                .name(request.getName())
                .cuisineType(request.getCuisineType())
                .contactInformation(request.getContentInformation())
                .address(address)
                .geoLocation(geoPoint)
                .operatingHours(request.getOperatingHours())
                .averageRating(0f)
                .photos(photos)
                .build();

        Restaurant save = restaurantRepository.save(build);
        return save;
    }

    @Override
    public Page<Restaurant> searchRestaurant(
            String query, Float minRating, Float latitude,
            Float longitude, Float radius, Pageable pageable) {

        if(null != minRating && (null == query || query.isEmpty())) {
            return restaurantRepository.findByAverageRatingGreaterThanEqual(minRating, pageable);
        }

        Float searchMinRating = null == minRating ? 0f : minRating;
        if(null != query && !query.trim().isEmpty()) {
            return restaurantRepository.findByQueryAndMinRating(query,searchMinRating, pageable);
        }

        if(null != latitude && null != longitude && null !=radius) {
            return restaurantRepository.findNearbyRestaurants(latitude,longitude,radius, pageable);
        }

        return restaurantRepository.findAll(pageable);
    }

    @Override
    public Optional<Restaurant> getRestaurant(String id) {
        Optional<Restaurant> byId = restaurantRepository.findById(id);
        return byId;
    }

    @Override
    public Restaurant updateRestaurant(String id, RestaurantCreateUpdateRequest request) {
        Restaurant restaurant = getRestaurant(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant with ID does not exist: " + id));

        GeoLocation newGeoLocation = geoLocationService.geoLocate(request.getAddress());

        GeoPoint newGeoPoint = new GeoPoint(newGeoLocation.getLatitude(), newGeoLocation.getLatitude());


        List<String> photoIds = request.getPhotoIds();
        List<Photo> photos = photoIds.stream().map(photoUrl -> Photo.builder()
                .url(photoUrl)
                .uploadDate(LocalDateTime.now())
                .build()).toList();

        restaurant.setName(request.getName());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setContactInformation(request.getContentInformation());
        restaurant.setAddress(request.getAddress());
        restaurant.setGeoLocation(newGeoPoint);
        restaurant.setOperatingHours(request.getOperatingHours());
        restaurant.setPhotos(photos);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public ResponseEntity<Void> deleteRestaurant(String id) {
        restaurantRepository.deleteById(id);
        return null;
    }

}
