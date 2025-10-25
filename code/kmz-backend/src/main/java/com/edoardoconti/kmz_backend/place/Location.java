package com.edoardoconti.kmz_backend.place;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class Location {
    @NotNull(message = "Latitude must be provided")
    @DecimalMin(value = "-90.0", inclusive = true, message = "Latitude must be ≥ -90.0")
    @DecimalMax(value = "90.0",  inclusive = true, message = "Latitude must be ≤ 90.0")
    private Double latitude;
    @NotNull(message = "Longitude must be provided")
    @DecimalMin(value = "-180.0", inclusive = true, message = "Longitude must be ≥ -180.0")
    @DecimalMax(value = "180.0", inclusive = true, message = "Longitude must be ≤ 180.0")
    private Double longitude;
}
