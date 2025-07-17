package com.edoardoconti.kmz_backend.process;

import com.edoardoconti.kmz_backend.content.Content;
import java.util.List;


public interface ProcessService {
    void uploadProcess(Content content);
    List<Content> getProcesses();
    Content getProcess(Long id);
}
