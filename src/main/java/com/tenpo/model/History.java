package com.tenpo.model;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface History {

    Page<HistoryResource> getAllHistory(final Integer pageSize, final Integer pageNumber, final String sortBy);
}
