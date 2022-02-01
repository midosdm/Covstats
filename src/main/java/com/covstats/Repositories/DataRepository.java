package com.covstats.Repositories;

import com.covstats.entities.CovData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<CovData, Long> {

}
