package com.prinved.agroApp.repository;

import com.prinved.agroApp.model.SemenceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemenceRepository extends JpaRepository<SemenceModel, Long> {

    public SemenceModel findSemenceByLabel(String label);
}
