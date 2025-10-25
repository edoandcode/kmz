package com.edoardoconti.kmz_backend.place;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    @NotBlank(message = "Name of place must not be blank")
    @Size(max = 100, message = "Name of place must be at most 100 characters")
    @Column(name = "place_name")
    private String name;
    @Column(name = "place_description")
    private String description;
    @Valid
    @Embedded
    private Location location;
}
