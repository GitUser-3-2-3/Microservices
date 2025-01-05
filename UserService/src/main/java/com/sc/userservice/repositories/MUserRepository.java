package com.sc.userservice.repositories;

import com.sc.userservice.entities.MUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MUserRepository extends JpaRepository<MUser, String> {
}
