package com.sc.userservice.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private String msg;
    private boolean reqSuccess;
    private HttpStatus httpStatus;
}
