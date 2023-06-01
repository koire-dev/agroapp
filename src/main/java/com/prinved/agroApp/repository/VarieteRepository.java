package com.prinved.agroApp.repository;

import com.prinved.agroApp.model.VarieteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VarieteRepository extends JpaRepository<VarieteModel, Long> {

    public VarieteModel findVarieteByLabel(String lable);
}
