package com.nisum.infrastructure.entrypoints;

import com.nisum.domain.model.session.Login;
import com.nisum.domain.model.user.User;
import com.nisum.domain.usercase.session.SessionOperations;
import com.nisum.domain.usercase.user.UserOperations;
import com.nisum.infraestructure.entrypoints.apirest.api.ApiUsers;
import com.nisum.infrastructure.entrypoints.datatest.GeneralContextMock;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest
@ContextConfiguration(classes = {ApiUsers.class})
class ApiUsersTest extends GeneralContextMock {

    @MockBean
    UserOperations userOperations;

    @MockBean
    SessionOperations sessionOperations;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockSecurityContext();
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void login() throws Exception {
        when(sessionOperations.login(any(Login.class))).thenReturn("any.mock.token");
        var response = mockMvc
                .perform(
                        post("/api/login")
                                .contentType(APPLICATION_JSON_VALUE)
                                .content("{\n" +
                                        "    \"email\": \"jmo@gmail.com\",\n" +
                                        "    \"password\": \"test321\"\n" +
                                        "}")
                )
                .andExpect(status().isOk())
                .andReturn();

        var actual = response.getResponse().getContentAsString();
        assertThat(actual).isNotNull();
    }

    @Test
    void save() throws Exception {
        when(userOperations.save(any(User.class))).thenReturn(User.builder().userId(UUID.randomUUID()).build());
        var response = mockMvc
                .perform(
                        post("/api/save")
                                .header(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX.concat(testToken()))
                                .contentType(APPLICATION_JSON_VALUE)
                                .content("{\n" +
                                        "    \"name\": \"prueba2\",\n" +
                                        "    \"email\": \"prueba@1.com\",\n" +
                                        "    \"password\": \"V941203R@r*\",\n" +
                                        "    \"phones\": [\n" +
                                        "        {\n" +
                                        "            \"number\": \"312625718\",\n" +
                                        "            \"cityCode\": \"1\",\n" +
                                        "            \"contryCode\": \"57\"\n" +
                                        "        }\n" +
                                        "    ]\n" +
                                        "}")
                )
                .andExpect(status().isCreated())
                .andReturn();

        var actual = response.getResponse().getContentAsString();
        assertThat(actual).isNotNull();
    }

}
