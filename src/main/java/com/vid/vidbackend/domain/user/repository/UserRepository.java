package com.vid.vidbackend.domain.user.repository;

import com.vid.vidbackend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long, User> {
}
