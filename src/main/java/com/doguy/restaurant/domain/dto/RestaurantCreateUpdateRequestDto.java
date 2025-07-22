package com.doguy.restaurant.domain.dto;

import com.doguy.restaurant.domain.entities.Address;
import com.doguy.restaurant.domain.entities.OperatingHours;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantCreateUpdateRequestDto {
    @NotBlank(message = "Restaurant name is required")
    private String name;
    @NotBlank(message = "Cuisine Type is required")
    private String cuisineType;
    @NotBlank(message = "Contact information is required")
    private String contentInformation;
    @Valid
    private AddressDto address;
    @Valid
    private OperatingHoursDto operatingHours;
    @Size(min = 1, message = "At lest one photo is required")
    private List<String> photoIds;
}
