package com.sc.hotelservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
    private String aboutUser;
}
