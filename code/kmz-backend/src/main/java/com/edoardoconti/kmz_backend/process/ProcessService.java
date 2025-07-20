package com.edoardoconti.kmz_backend.process;

import java.util.List;

public interface ProcessService {
    void uploadProcess(Process process);
    List<Process> getProcesses();
    Process getProcess(Long id);
}
