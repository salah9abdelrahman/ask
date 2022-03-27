package com.salah.ask.head_first;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @GetMapping("/foo")
    public String foo() {
        return "boo";
    }

    @PostMapping("/person")
    public Person getPerson(@RequestBody PersonRequest personRequest){
        return new Person(personRequest.getName(), "bla");
    }


}

@NoArgsConstructor
@Setter
@Getter
class Person {
    String name;
    String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}

@NoArgsConstructor
@Setter
@Getter
class PersonRequest {
    String name;
}