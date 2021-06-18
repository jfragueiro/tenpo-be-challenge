package com.tenpo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class OperationResponse {

    private Double result;
    private Error error;

    public OperationResponse(Double result) {
        this.result = result;
    }

    public OperationResponse(Error e) {
        this.error = e;
    }

}
