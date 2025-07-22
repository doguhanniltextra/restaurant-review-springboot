package com.doguy.restaurant.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {
    @NotBlank(message = "Street number is required")
    private String streetNumber;
    @NotBlank(message = "Street name is required")
    private String streetName;
    private String unit;
    @NotBlank(message = "City name is required")
    private String city;
    @NotBlank(message = "State name is required")
    private String state;
    @NotBlank(message = "Postal code is required")
    private String postalCode;
    @NotBlank(message = "Country name is required")
    private String country;
}
