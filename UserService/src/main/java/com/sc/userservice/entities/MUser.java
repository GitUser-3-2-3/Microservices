package com.sc.userservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "micro_users")
@NoArgsConstructor
@AllArgsConstructor
public class MUser {

    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "USERNAME", length = 25)
    private String userName;

    @Column(name = "EMAIL")
    private String userEmail;

    @Column(name = "ABOUT")
    private String aboutUser;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
