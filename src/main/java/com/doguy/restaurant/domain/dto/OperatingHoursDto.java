package com.doguy.restaurant.domain.dto;

import com.doguy.restaurant.domain.entities.TimeRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperatingHoursDto {
    private TimeRangeDto monday;
    private TimeRangeDto tuesday;
    private TimeRangeDto wednesday;
    private TimeRangeDto thursday;
    private TimeRangeDto friday;
    private TimeRangeDto saturday;
    private TimeRangeDto sunday;
}
