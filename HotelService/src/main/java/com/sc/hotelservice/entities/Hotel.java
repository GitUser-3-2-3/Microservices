package com.sc.hotelservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "hotels")
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @Column(name = "id")
    private String hotelId;

    @Column(name = "hotel_name", length = 25)
    private String hotelName;

    @Column(name = "location")
    private String location;

    @Column(name = "about")
    private String aboutHotel;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
