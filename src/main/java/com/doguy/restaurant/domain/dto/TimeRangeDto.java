package com.doguy.restaurant.domain.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeRangeDto {
    @NotBlank(message = "Open time must be provided")
    private String openTime;
    @NotBlank(message = "Close time must be provided")
    private String closeTime;
}
