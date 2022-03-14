package com.salah.ask.service;

import com.salah.ask.model.Todo;
import com.salah.ask.model.ask.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ARestClientService {

    private  WebClient webClient;

    public ARestClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public  List<Todo> getTodoList(){
      List<Todo> todos = webClient.get().uri("todos")
                .retrieve()
              .bodyToFlux(Todo.class)
              .collectList()
              .block();

      return todos;
    }

    public  Todo getTodoById(Integer id){
        Todo todo = webClient.get().uri("todos/" + id)
                .retrieve()
                .bodyToMono(Todo.class)
                .block();

        return todo;
    }
}
