package com.doguy.restaurant.domain.entities;

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
public class OperatingHours {

    /**
     * "monday": {
     *   "start": "08:00",
     *   "end": "17:00"
     * }
     */
    @Field(type = FieldType.Nested)
    private TimeRange monday;

    @Field(type = FieldType.Nested)
    private TimeRange tuesday;

    @Field(type = FieldType.Nested)
    private TimeRange wednesday;


    @Field(type = FieldType.Nested)
    private TimeRange thursday;

    @Field(type = FieldType.Nested)
    private TimeRange friday;

    @Field(type = FieldType.Nested)
    private TimeRange saturday;

    @Field(type = FieldType.Nested)
    private TimeRange sunday;
}
