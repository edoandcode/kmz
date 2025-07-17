package com.edoardoconti.kmz_backend.process;

import com.edoardoconti.kmz_backend.content.Content;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealProcessService implements ProcessService{

    private ProcessRepository repository;

    public RealProcessService(ProcessRepository repository) {
        this.repository = repository;
    }

    @Override
    public void uploadProcess(Content content) {
        this.repository.save(content);
    }

    @Override
    public List<Content> getProcesses() {
        return this.repository.getProcesses();
    }

    @Override
    public Content getProcess(Long id) {
        return this.repository.getProduct(id);
    }
}
