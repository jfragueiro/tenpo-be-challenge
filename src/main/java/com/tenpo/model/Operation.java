package com.tenpo.model;

import org.springframework.stereotype.Repository;

@Repository
public interface Operation {

    Double multiply(final Double a, final Double b);
}
