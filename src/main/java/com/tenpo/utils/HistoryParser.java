package com.tenpo.utils;

import com.tenpo.model.HistoryResource;
import com.tenpo.persistence.HistoryEntity;

public class HistoryParser {
    public static HistoryEntity parse(final HistoryResource historyResource) {
        final HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setId(historyResource.getId() != null ? historyResource.getId() : 0);
        historyEntity.setUrl(historyResource.getUrl());
        return historyEntity;
    }

    public static HistoryResource parse(final HistoryEntity entity){
        final HistoryResource historyResource = new HistoryResource();
        historyResource.setId(entity.getId());
        historyResource.setUrl(entity.getUrl());
        return historyResource;
    }

}
