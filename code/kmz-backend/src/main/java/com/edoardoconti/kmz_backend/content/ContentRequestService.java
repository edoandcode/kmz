package com.edoardoconti.kmz_backend.content;


import com.edoardoconti.kmz_backend.curator.CuratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentRequestService {

    private final ContentRequestRepository contentRequestRepository;
    private final CuratorService curatorService;

    void addRequest(Long authorId, Long contentId) {
        Long curatorId = curatorService.requestCurator();
        var request = new ContentApprovalRequest(authorId, contentId, curatorId);
        contentRequestRepository.save(request);
    }

}
