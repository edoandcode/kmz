package com.edoardoconti.kmz_backend.product;

import com.edoardoconti.kmz_backend.content.Content;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Content {

    private String name;
    private String description;
    private Date sowingDate;
    private Date harvestDate;

    private String cultivationMethod;
    private String[] certification;
}
