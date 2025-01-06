package com.sc.userservice.entities;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private String ratingId;
    private String userId;
    private String hotelId;
    private Hotel hotel;
    private int rating;
    private String feedback;
}
