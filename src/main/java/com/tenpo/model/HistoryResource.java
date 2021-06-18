package com.tenpo.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HistoryResource {

    private Long id;
    private String url;

    public HistoryResource() {
    }

    public HistoryResource(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public HistoryResource(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryResource user = (HistoryResource) o;
        return Objects.equals(id, user.id) &&
                url.equals(user.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url);
    }

}
