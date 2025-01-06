package com.sc.ratingservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "user_ratings")
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @Column(name = "id")
    private String ratingId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "hotel_id")
    private String hotelId;

    @Column(name = "ratings")
    private int rating;

    @Column(name = "feedbacks")
    private String feedback;
}
