package com.salah.ask.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.jayway.restassured.RestAssured;
import com.salah.ask.model.Todo;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = "base_client_url=http://localhost:8888")
public class WireMockFirstHead {

    @Autowired
    ARestClientService aRestClientService;


    static Options options = wireMockConfig()
            .port(8888)
            // for debug information on console
            .notifier(new ConsoleNotifier(true))
            // to generate dynamic responses
            .extensions(new ResponseTemplateTransformer(true));

    public static WireMockServer wireMockServer = new WireMockServer(options);

    @BeforeClass
    public static void beforeClass() throws Exception {
        wireMockServer.start();
    }


    @AfterClass
    public static void afterClass() throws Exception {
        wireMockServer.stop();
    }

    @Test
    public void getTodos_success() {
        //given
        wireMockServer.stubFor(get(urlEqualTo("/todos"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("todos.json")
                )
        );

        //when
        List<Todo> todos = aRestClientService.getTodoList();

        System.out.println(todos);

        //then
        Assertions.assertTrue(todos.size() > 0);
    }


    @Test
    public void getOneTodo_success() {
        //given
        wireMockServer.stubFor(get(urlPathMatching("/todos/[0-9]"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("todo.json")
                )
        );

        //when
        Todo todo = aRestClientService.getTodoById(1);

        System.out.println(todo);

        //then
        Assertions.assertFalse(todo.isCompleted());
        Assertions.assertEquals("study wireMock yo", todo.getTitle());
    }

    @Test
    public void getOneTodo_responseTemplate_success() {
        //given
        wireMockServer.stubFor(get(urlPathMatching("/todos/[0-9]"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("todo-response-template.json")
                )
        );

        Integer todoId = 8;

        //when
        Todo todo = aRestClientService.getTodoById(todoId);

        System.out.println(todo);

        //then
        Assertions.assertEquals(todoId, todo.getId());
    }

    @Test
    public void getOneTodo_success_using_rest_assured() {

        //given
        wireMockServer.stubFor(get(urlPathMatching("/todos/1"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("todo.json")
                )
        );

        RestAssured.given()
                .baseUri("http://localhost:8888")
                .when()
                .get("/todos/{id}", 1)
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("id", Matchers.equalTo(1),
                        "userId", Matchers.equalTo(1),
                        "title", Matchers.equalTo("study wireMock yo"),
                        "completed", Matchers.equalTo(false)
                );
    }
}
