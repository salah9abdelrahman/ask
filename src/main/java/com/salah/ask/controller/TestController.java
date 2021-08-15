package com.salah.ask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String helloWorld(
//            @RequestHeader(name = "Accept-Language", required = false) Locale locale
    ) {
        return "<h1>" + messageSource.getMessage("i18n.hello", null, LocaleContextHolder.getLocale()) + "<h1/>";
    }
}
