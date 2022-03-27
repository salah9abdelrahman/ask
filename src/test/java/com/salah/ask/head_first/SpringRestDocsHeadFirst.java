package com.salah.ask.head_first;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SpringRestDocsHeadFirst {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Rule
    public JUnitRestDocumentation jUnitRestDocumentation = new JUnitRestDocumentation();

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(MockMvcRestDocumentation.documentationConfiguration(jUnitRestDocumentation))
                .build();
    }

    @Test
    public void should_return_boo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/foo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("boo"))
                .andDo(MockMvcRestDocumentation.document("index"));
    }

    @Test
    public void should_return_person() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/person")
                        .content("{\"name\":\"foo\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"name\":\"foo\",\"surname\":\"bla\"}"))
                .andDo(MockMvcRestDocumentation.document("person",
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("name")
                                        .type(JsonFieldType.STRING)
                                        .description("person request name")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("name")
                                        .type(JsonFieldType.STRING)
                                        .description("person's name"),
                                PayloadDocumentation.fieldWithPath("surname")
                                        .type(JsonFieldType.STRING)
                                        .description("person's surname")
                        )));

    }

}
