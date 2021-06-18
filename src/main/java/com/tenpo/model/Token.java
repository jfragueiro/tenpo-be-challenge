package com.tenpo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Token {

    private String token;

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }

}
