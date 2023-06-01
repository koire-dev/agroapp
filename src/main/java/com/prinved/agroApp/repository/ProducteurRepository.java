package com.prinved.agroApp.repository;

import com.prinved.agroApp.model.ProducteurModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducteurRepository extends JpaRepository<ProducteurModel, Long> {

    public List<ProducteurModel> findProducteurByNom(String nom);
}
