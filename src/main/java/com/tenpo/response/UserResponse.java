package com.tenpo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tenpo.model.Token;
import com.tenpo.model.UserResource;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class UserResponse {

    private UserResource userResource;
    private Token token;
    private Error error;

    public UserResponse(UserResource userResource, Token token, Error e) {
        this.userResource = userResource;
        this.token = token;
        this.error = e;
    }

    public UserResponse(UserResource userResource) {
        this.userResource = userResource;
    }

    public UserResponse(Token token) {
        this.token = token;
    }

    public UserResponse(Error e) {
        this.error = e;
    }

}
