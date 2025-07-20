package com.edoardoconti.kmz_backend.process;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealProcessService implements ProcessService{

    private ProcessRepository repository;

    public RealProcessService(ProcessRepository repository) {
        this.repository = repository;
    }

    @Override
    public void uploadProcess(Process process) {
        this.repository.save(process);
    }

    @Override
    public List<Process> getProcesses() {
        return this.repository.getProcesses();
    }

    @Override
    public Process getProcess(Long id) {
        return this.repository.getProduct(id);
    }
}
