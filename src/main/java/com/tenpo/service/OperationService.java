package com.tenpo.service;

import com.tenpo.model.Operation;
import org.springframework.stereotype.Service;

@Service
public class OperationService implements Operation {

    public Double multiply(final Double a, final Double b) {
        return a * b;
    }
}
