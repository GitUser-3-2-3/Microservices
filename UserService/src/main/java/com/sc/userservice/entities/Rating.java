package com.sc.userservice.entities;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private String ratingId;
    private int rating;
    private String hotelId;
    private Hotel hotel;
    private String feedback;
}
