package com.edoardoconti.kmz_backend.content;

import com.edoardoconti.kmz_backend.common.RequestStatus;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="approval_requests")
@Getter
@Setter
@NoArgsConstructor
public class ContentApprovalRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long authorId;
    private Long contentId;
    private Long curatorId;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date processedAt;

    public ContentApprovalRequest(Long authorId, Long contentId, Long curatorId) {
        this.authorId = authorId;
        this.contentId = contentId;
        this.curatorId = curatorId;
        this.status = RequestStatus.PENDING;
        this.requestedAt = new Date();
    }
}
