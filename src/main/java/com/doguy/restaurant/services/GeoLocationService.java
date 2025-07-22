package com.doguy.restaurant.services;

import com.doguy.restaurant.domain.entities.Address;
import com.doguy.restaurant.domain.entities.GeoLocation;

public interface GeoLocationService {
    GeoLocation geoLocate(Address address);
}
