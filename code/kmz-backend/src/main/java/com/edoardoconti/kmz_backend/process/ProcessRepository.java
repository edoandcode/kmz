package com.edoardoconti.kmz_backend.process;

import com.edoardoconti.kmz_backend.content.ContentType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProcessRepository {
    private final List<Process> processes = new ArrayList<>();
    private Long nextId = 1L;

    public void save(Process process) {
        if(process.getType() != ContentType.PROCESS)
            return;
        process.setId(nextId++);
        processes.add(process);
    }

    public List<Process> getProcesses() {
        return processes;
    }


    public Process getProduct(Long id) {
        return processes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

    }
}
