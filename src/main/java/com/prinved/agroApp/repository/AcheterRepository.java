package com.prinved.agroApp.repository;

import com.prinved.agroApp.model.Acheter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface AcheterRepository extends JpaRepository<Acheter, Long> {

    public List<Acheter> findVarieteByDate(Date date);
}
