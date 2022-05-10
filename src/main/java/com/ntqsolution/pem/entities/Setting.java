package com.ntqsolution.pem.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "setting")
@Getter
@Setter
public class Setting {
    @Id
    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;
}
