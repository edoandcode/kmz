package com.edoardoconti.kmz_backend.process;

import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.content.ContentType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProcessRepository {
    private final List<Content> processes = new ArrayList<>();
    private Long nextId = 1L;

    public void save(Content content) {
        if(content.getType() != ContentType.PROCESS)
            return;
        content.setId(nextId++);
        processes.add(content);
    }

    public List<Content> getProcesses() {
        return processes;
    }


    public Content getProduct(Long id) {
        return processes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

    }
}
