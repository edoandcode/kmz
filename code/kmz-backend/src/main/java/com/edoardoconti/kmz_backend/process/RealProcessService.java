package com.edoardoconti.kmz_backend.process;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealProcessService implements ProcessService{

    private final ProcessRepository repository;

    @Override
    public Process uploadProcess(Process process) {
        return this.repository.save(process);
    }

    @Override
    public List<Process> getProcesses() {
        return this.repository.findAll();
    }

    @Override
    public Process getProcess(Long id) {
        return this.repository.findById(id).orElse(null);
    }
}
