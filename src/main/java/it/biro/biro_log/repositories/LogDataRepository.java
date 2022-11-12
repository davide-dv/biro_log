package it.biro.biro_log.repositories;

import it.biro.biro_log.entities.LogData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface LogDataRepository extends JpaRepository<LogData, Long> {

    Collection<LogData> findByOrigin(String origin);

    Collection<LogData> findByDateBetween(Date start, Date end);

    Collection<LogData> findByOriginAndDateBetween(String origin, Date start, Date end);
}
