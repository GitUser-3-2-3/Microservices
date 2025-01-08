package com.sc.hotelservice.entities;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private String ratingId;
    private int rating;
    private String userId;
    private MUser user;
    private String feedback;
}
