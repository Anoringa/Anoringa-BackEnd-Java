package com.pelucasistemas.anoringa.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntidadPersistente {
    @Id
    @GeneratedValue
    private Long id;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
