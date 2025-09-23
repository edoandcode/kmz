package com.edoardoconti.kmz_backend.product;
import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.content.ContentType;
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
    private List<String> certification;


    public ProductContent() {
        super(ContentType.PRODUCT);
    }


}