package com.doguy.restaurant.mappers;

import com.doguy.restaurant.domain.dto.GeoPointDto;
import com.doguy.restaurant.domain.dto.RestaurantCreateUpdateRequestDto;
import com.doguy.restaurant.domain.dto.RestaurantDto;
import com.doguy.restaurant.domain.entities.Restaurant;
import com.doguy.restaurant.domain.entities.RestaurantCreateUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {
    RestaurantCreateUpdateRequest toRestaurantCreateUpdateRequest(RestaurantCreateUpdateRequestDto dto);

    RestaurantDto toRestaurantDto(Restaurant restaurant);

    @Mapping(target = "latitude", expression = "java(geoPoint.getLat())")
    @Mapping(target = "longitude", expression = "java(geoPoint.getLong())")
    GeoPointDto toGeoPointDto(GeoPoint geoPoint);
}
