package it.biro.biro_log.services;

import it.biro.biro_log.entities.LogData;
import it.biro.biro_log.repositories.LogDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class LogDataService {

    @Autowired
    private LogDataRepository logDataRepository;

    public LogData save(LogData logData) {
        return logDataRepository.save(logData);
    }

    public Collection<LogData> findByOrigin(String Origin) {
        return logDataRepository.findByOrigin(Origin);
    }

    public Collection<LogData> findByDateBetween(Date start, Date end) {
        return logDataRepository.findByDateBetween(start, end);
    }

    public Collection<LogData> findByOriginAndDateBetween(String origin, Date start, Date end) {
        return logDataRepository.findByOriginAndDateBetween(origin, start, end);
    }

}
