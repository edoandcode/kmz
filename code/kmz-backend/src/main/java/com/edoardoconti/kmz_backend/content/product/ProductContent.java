package com.edoardoconti.kmz_backend.content.product;
import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.content.ContentType;
import com.edoardoconti.kmz_backend.place.Place;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="products")
@Getter @Setter
public class ProductContent extends Content {
    private String name;
    private String description;
    private Date sowingDate;
    private Date harvestDate;
    private String cultivationMethod;
    @ElementCollection
    private List<String> certifications;
    @Embedded
    private Place cultivationPlace;

    public ProductContent() {
        super(ContentType.PRODUCT);
    }
}