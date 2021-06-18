package com.tenpo.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserResource {

    private Long id;
    private String email;
    private String password;

    public UserResource() {
    }

    public UserResource(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UserResource(String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResource userResource = (UserResource) o;
        return Objects.equals(id, userResource.id) &&
                email.equals(userResource.email) &&
                password.equals(userResource.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }
}
