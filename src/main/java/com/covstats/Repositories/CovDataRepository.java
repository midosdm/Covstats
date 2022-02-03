package com.covstats.Repositories;

import com.covstats.entities.CovData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CovDataRepository extends JpaRepository<CovData, Long> {
    List<CovData> findByPays(String pays);
    CovData findByPaysAndDate(String pays, LocalDate date);
}
