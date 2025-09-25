package com.edoardoconti.kmz_backend.request;




import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@NoArgsConstructor
@Service
public abstract class RequestService {

    protected final <T extends Request> T getApprovedRequest(T request, String message) {
        this.checkRequestProcessed(request);
        request.setStatus(RequestStatus.APPROVED);
        request.setProcessedAt(new Date());
        if (message != null && !message.isBlank()) {
            request.setMessage(message);
        }
        return request;
    }

    protected final <T extends Request> T getRejectedRequest(T request, String message) {
        this.checkRequestProcessed(request);
        request.setStatus(RequestStatus.REJECTED);
        request.setProcessedAt(new Date());
        if(message != null && !message.isBlank())
            request.setMessage(message);
        return request;
    }

    public abstract void approveRequest(Long requestId, String message);
    public abstract void rejectRequest(Long requestId, String message);


    ///  PRIVATE METHODS

    private void checkRequestProcessed(Request request) {
        if(request.getProcessedAt() != null)
            throw new IllegalStateException("Request already processed");
    }
}
