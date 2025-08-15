package com.edoardoconti.kmz_backend.content;

import com.edoardoconti.kmz_backend.common.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRequestRepository extends JpaRepository<ContentApprovalRequest, Long> {
    List<ContentApprovalRequest> findByStatus(RequestStatus status);
}
