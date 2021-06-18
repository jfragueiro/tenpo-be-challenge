package com.tenpo.model;

import org.springframework.stereotype.Repository;

@Repository
public interface User {
    boolean userExist(String email) throws RuntimeException;

    String validateUser(UserResource userResource) throws RuntimeException;
}
