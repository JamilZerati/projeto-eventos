package org.projeto.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Pageable {

    @JsonIgnore
    private Integer page;
    @JsonIgnore
    private Integer size;

    public Pageable(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public Pageable() {
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }
}
