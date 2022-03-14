package com.salah.ask.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Todo {
    Integer userId;
    Integer id;
    String title;
    boolean completed;

    @Override
    public String toString() {
        return "Todo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }


}
