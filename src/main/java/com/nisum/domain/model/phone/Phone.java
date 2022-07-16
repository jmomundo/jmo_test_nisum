package com.nisum.domain.model.phone;


import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phone {

    private UUID phoneId;
    private Integer number;
    private Integer cityCode;
    private Integer contryCode;

}
