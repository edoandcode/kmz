package com.edoardoconti.kmz_backend.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private final Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date processedAt;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Column(length = 500)
    private String message;

    @Column(name="requester_id")
    private final Long requesterId;

    @Column(name="recipient_id")
    private final Long recipientId;

    private final RequestType type;


    public Request(RequestType type, Long requesterId) {
        this.type = type;
        this.requesterId = requesterId;
        this.createdAt = new Date();
        this.status = RequestStatus.PENDING;
        this.processedAt = null;
        this.message = null;
        this.recipientId = null;
    }

    public Request(RequestType type, Long requesterId, Long recipientId) {
        this.type = type;
        this.requesterId = requesterId;
        this.createdAt = new Date();
        this.status = RequestStatus.PENDING;
        this.processedAt = null;
        this.message = null;
        this.recipientId = recipientId;
    }








}
