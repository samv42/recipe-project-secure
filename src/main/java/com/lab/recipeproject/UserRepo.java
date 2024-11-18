package com.lab.recipeproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<CustomUserDetails, Long> {
    CustomUserDetails findByUsername(String username);
}
