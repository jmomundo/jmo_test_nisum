package com.nisum.domain.model.common;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {
    private String code;
    private String message;
    private String timestamp;

}
