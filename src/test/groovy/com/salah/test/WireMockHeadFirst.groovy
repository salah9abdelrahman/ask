//package com.salah.test
//
//import com.github.tomakehurst.wiremock.WireMockServer
//import com.github.tomakehurst.wiremock.common.ConsoleNotifier
//import com.github.tomakehurst.wiremock.core.Options
//import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer
//import com.github.tomakehurst.wiremock.junit.WireMockRule
//import com.salah.ask.model.Todo
//import com.salah.ask.head_first.ARestClientService
//import org.junit.Rule
//import org.junit.runner.RunWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.http.HttpHeaders
//import org.springframework.http.HttpStatus
//import org.springframework.http.MediaType
//import org.springframework.test.context.TestPropertySource
//import org.springframework.test.context.junit4.SpringRunner
//import spock.lang.Specification
//import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
//import static com.github.tomakehurst.wiremock.client.WireMock.get
//import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
//import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@TestPropertySource(properties = "base_client_url=http://localhost:8888")
//class WireMockHeadFirst extends Specification {
//
//    @Autowired
//    ARestClientService aRestClientService
//
//     Options options = wireMockConfig()
//            .port(8888)
//            .notifier(new ConsoleNotifier(true))
//            .extensions(new ResponseTemplateTransformer(true))
//
//    @Rule
//    public  WireMockRule wireMockServer = new WireMockRule(options)
//
//
////    void setupSpec() {
////        wireMockServer.start()
////    }
////
////
////    void cleanupSpec() {
////        wireMockServer.stop()
////    }
////
////    void cleanup() {
////        wireMockServer.resetAll()
////    }
//
//    def  "get todos success"() {
//        given:
//        wireMockServer.stubFor(get(urlEqualTo("/todos"))
//                .willReturn(aResponse()
//                        .withStatus(HttpStatus.OK.value())
//                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                        .withBodyFile("todos.json")
//                )
//        )
//
//        when:
//        List<Todo> todos = aRestClientService.getTodoList()
//
//        System.out.println(todos);
//
//        then:
//        todos.size() > 0
//    }
//
//     def "get one todo"() {
//         println "Sss" + wireMockServer.isRunning()
//        given:
//        wireMockServer.stubFor(get(urlEqualTo("/todos/1"))
//                .willReturn(aResponse()
//                        .withStatus(HttpStatus.OK.value())
//                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                        .withBodyFile("todo.json")
//                )
//        )
//
//        when:
//        Todo todo = aRestClientService.getTodoById(1)
//
//        System.out.println(todo)
//
//        then:
//        !todo.isCompleted()
//    }
//}