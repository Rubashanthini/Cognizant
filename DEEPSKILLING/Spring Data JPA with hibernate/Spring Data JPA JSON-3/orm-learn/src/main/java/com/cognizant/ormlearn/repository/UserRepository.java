package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
