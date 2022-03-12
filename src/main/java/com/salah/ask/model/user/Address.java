package com.salah.ask.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@NoArgsConstructor
@Setter
@Getter
public class Address {
    //Open issue:  @NotNull  Ignored for DDL generation, so we have to use @Column
    @NotNull
    @Column(nullable = false)
    public String street;
    @NotNull
    @Column(nullable = false)
    public String zipcode;
    @NotNull
    @Column(nullable = false)
    public String city;
}
