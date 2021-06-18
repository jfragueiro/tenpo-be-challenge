package com.tenpo.service;

import com.tenpo.model.HistoryResource;
import com.tenpo.persistence.HistoryEntity;
import com.tenpo.persistence.HistoryRepository;
import com.tenpo.utils.HistoryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoryService implements com.tenpo.model.History {

    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public Page<HistoryResource> getAllHistory(final Integer pageSize, final Integer pageNumber, final String sortBy) {
        int size = pageSize != null && pageSize != 0 ? pageSize : 10;
        int page = pageNumber != null ? pageNumber : 0;
        String sort = sortBy != null ? sortBy : "id";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        List<HistoryEntity> lisat = historyRepository.findAll();

        List<HistoryResource> list = historyRepository.findAll(pageable).stream()
                .map(HistoryParser::parse)
                .collect(Collectors.toList());

        return new PageImpl<>(list);
    }

    public HistoryResource save(final HistoryResource historyResource) {
        final HistoryEntity entity = historyRepository.save(HistoryParser.parse(historyResource));
        return HistoryParser.parse(entity);
    }

    public List<HistoryResource> getAll() {
        return historyRepository.findAll()
                .stream()
                .map(HistoryParser::parse)
                .collect(Collectors.toList());
    }

    public Optional<HistoryResource> get(Long id) {
        return historyRepository.findById(id).map(HistoryParser::parse);
    }
}
