package com.edoardoconti.kmz_backend.content.processed;

import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.content.ContentType;
import com.edoardoconti.kmz_backend.content.process.ProcessContent;
import com.edoardoconti.kmz_backend.content.product.ProductContent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="processed_products")
@Getter @Setter
public class ProcessedProductContent extends Content {
    private String name;
    private String description;
    private Date sowingDate;
    private Date harvestDate;
    private String cultivationMethod;
    @ElementCollection
    private List<String> certifications;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "processed_products_processes",
            joinColumns = @JoinColumn(name = "processed_products_id"),
            inverseJoinColumns = @JoinColumn(name = "processes_id")
    )
    private List<ProcessContent> processes;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "processed_products_ingredients",
            joinColumns = @JoinColumn(name = "processed_products_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id")
    )
    private List<ProductContent> ingredients;

    public ProcessedProductContent() {
        super(ContentType.PROCESSED_PRODUCT);
    }
}