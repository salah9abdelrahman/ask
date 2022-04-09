package com.salah.ask.service.sax;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Staff {
    private Long id;
    private String name;
    private String role;
    private BigDecimal salary;
    private String Currency;
    private String bio;

}