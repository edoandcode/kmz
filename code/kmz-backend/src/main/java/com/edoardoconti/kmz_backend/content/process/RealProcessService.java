package com.edoardoconti.kmz_backend.content.process;

import com.edoardoconti.kmz_backend.content.ContentStatus;
import com.edoardoconti.kmz_backend.content.ContentType;
import com.edoardoconti.kmz_backend.feed.FeedService;
import com.edoardoconti.kmz_backend.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealProcessService implements ProcessService{

    private final ProcessRepository repository;
    private final ProcessContentMapper processContentMapper;
    private final AuthService authService;
    private final FeedService feedService;


    @Override
    public ProcessContentDto uploadProcess(ProcessContentDto processContentDto) {
        var process = this.processContentMapper.toEntity(processContentDto);
        process.setStatus(ContentStatus.DRAFT);
        var currentUser = this.authService.getCurrentUser();
        process.setAuthorId(currentUser.getId());
        this.repository.save(process);
        return this.processContentMapper.toDto(process);
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

    @Override
    public List<ProcessContentDto> getMyProcesses() {
        var currentUser = this.authService.getCurrentUser();
        return this.repository.findAllByAuthorId(currentUser.getId())
                .stream()
                .map(this.processContentMapper::toDto)
                .toList();
    }

    @Override
    public ProcessContentDto getMyProcess(Long id) {
        var currentUser = this.authService.getCurrentUser();
        return this.repository.findAllByAuthorId(currentUser.getId())
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(this.processContentMapper::toDto)
                .orElse(null);
    }

    @Override
    public ProcessContentDto deleteProcess(Long id) {
        var process = this.repository.findById(id).orElse(null);
        var dto = this.processContentMapper.toDto(process);
        if(process == null)
            return null;
        var currentUser = this.authService.getCurrentUser();
        if (!process.getAuthorId().equals(currentUser.getId()))
            return null;
        this.feedService.unpublishContent(process.getId());
        this.repository.delete(process);
        return dto;
    }
}
