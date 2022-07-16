package com.nisum.application.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.domain.model.common.ErrorResponseDTO;
import com.nisum.domain.model.common.enums.ApplicationExceptionEnum;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        var out = response.getOutputStream();
        new ObjectMapper().writeValue(
                out, new ErrorResponseDTO(
                        ApplicationExceptionEnum.FORBIDDEN.getCode(),
                        "FORBIDDEN",
                        LocalDateTime.now().toString()
                )
        );
        out.flush();
    }
}
