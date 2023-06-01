package com.prinved.agroApp.repository;

import com.prinved.agroApp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
