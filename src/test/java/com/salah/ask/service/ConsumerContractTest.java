package com.salah.ask.service;


import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerContractTest {

    @Rule
    public StubRunnerRule stubRunnerRule = new StubRunnerRule()
            .downloadStub("com.salah:microdudekman:0.0.1-SNAPSHOT:stubs")
            .withPort(8181)
            .stubsMode(StubRunnerProperties.StubsMode.LOCAL);

    @Test
    public void should_return_list_of_names() throws Exception {
        List<String> response = List.of("Salah", "Abdulrahman");


        List actualResponse = new RestTemplate()
                .getForObject("http://localhost:8181/provider/list", List.class);

        Assertions.assertEquals(response.get(0), actualResponse.get(0));
        Assertions.assertEquals(response.get(1), actualResponse.get(1));
    }
}
