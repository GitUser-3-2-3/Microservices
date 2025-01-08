package com.sc.hotelservice.entities;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MUser {
    private String userId;
    private String userName;
    private String userEmail;
    private String aboutUser;
}
