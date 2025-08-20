package com.edoardoconti.kmz_backend.process;

import java.util.List;

public interface ProcessService {
    ProcessContentDTO uploadProcess(ProcessContentDTO processContent);
    List<ProcessContentDTO> getProcesses();
    ProcessContentDTO getProcess(Long id);
}
