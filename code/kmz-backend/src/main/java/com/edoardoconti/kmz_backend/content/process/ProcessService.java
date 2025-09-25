package com.edoardoconti.kmz_backend.content.process;

import java.util.List;

public interface ProcessService {
    ProcessContentDto uploadProcess(ProcessContentDto processContent);
    List<ProcessContentDto> getProcesses();
    ProcessContentDto getProcess(Long id);
    List<ProcessContentDto> getMyProcesses();
    ProcessContentDto getMyProcess(Long id);
}
