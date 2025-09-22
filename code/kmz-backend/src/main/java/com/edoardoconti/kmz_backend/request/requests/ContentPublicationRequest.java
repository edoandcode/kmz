package com.edoardoconti.kmz_backend.request.requests;


import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.request.Request;
import com.edoardoconti.kmz_backend.request.RequestType;
import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name="content_publication_requests")
public class ContentPublicationRequest extends Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message="content must not be null")
    @Valid
    @JoinColumn(name = "content_id", nullable = false)
    private Long contentId;

    protected ContentPublicationRequest() {
        super(RequestType.CONTENT_PUBLICATION, null);
    }


    public ContentPublicationRequest(Long requesterId, Long contentId) {
        super(RequestType.CONTENT_PUBLICATION, requesterId);
        this.contentId = contentId;
    }
}
