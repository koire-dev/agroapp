package com.prinved.agroApp.repository;

import com.prinved.agroApp.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientModel,Long> {
}
