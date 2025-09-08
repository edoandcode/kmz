package com.edoardoconti.kmz_backend.process;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealProcessService implements ProcessService{

    private final ProcessRepository repository;
    private final ProcessContentMapper processContentMapper;

    @Override
    public ProcessContentDto uploadProcess(ProcessContentDto processContentDto) {

        var process = this.processContentMapper.toEntity(processContentDto);
        this.repository.save(process);
        processContentDto.setId(process.getId());
        return processContentDto;
    }

    @Override
    public List<ProcessContentDto> getProcesses() {
        return this.repository.findAll()
                .stream()
                .map(this.processContentMapper::toDto)
                .toList();
    }

    @Override
    public ProcessContentDto getProcess(Long id) {
        var process = this.repository.findById(id).orElse(null);
        return this.processContentMapper.toDto(process);
    }
}
