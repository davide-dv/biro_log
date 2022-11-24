package it.biro.biro_log.controllers;

import it.biro.biro_log.entities.LogData;
import it.biro.biro_log.services.LogDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/log")
public class Controller {

    @Autowired
    private LogDataService logDataService;

    @GetMapping("/")
    public Mono<String> get() {
        return Mono.just("ok");
    }

    @PostMapping("/")
    public Mono<LogData> logString(@RequestBody LogData logData) {
        return Mono.just(logDataService.save(LogData.builder()
                    .message(logData.getMessage())
                    .origin(logData.getOrigin())
                    .date(logData.getDate())
                    .build()));
    }

    @GetMapping("/origin/{origin}")
    public Flux<LogData> getByOrigin(@PathVariable String origin) {
        return Mono.just(logDataService.findByOrigin(origin)).flatMapMany(Flux::fromIterable);
    }

    @GetMapping("/origin/{origin}/{startDate}/{endDate}")
    public Flux<LogData> getByOriginAndDateBetween(@PathVariable String origin, @PathVariable String startDate, @PathVariable String endDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);
        return Mono.just(logDataService.findByOriginAndDateBetween(origin, start, end)).flatMapMany(Flux::fromIterable);
    }

    @GetMapping("/date/{startDate}/{endDate}")
    public Flux<LogData> getByDateRange(@PathVariable String startDate, @PathVariable String endDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);
        System.out.println();
        return Mono.just(logDataService.findByDateBetween(start, end)).flatMapMany(Flux::fromIterable);
    }

}
