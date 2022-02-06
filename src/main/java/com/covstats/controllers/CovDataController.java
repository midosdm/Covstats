package com.covstats.controllers;

import com.covstats.entities.CovData;
import com.covstats.services.CovDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/covdata")
public class CovDataController {
    @Autowired
    private final CovDataService covDataService;

    private static final Logger logger = LogManager.getLogger(CovDataController.class);

    public CovDataController(CovDataService covDataService) {
        this.covDataService = covDataService;
    }

    @GetMapping()
    public List<CovData> getAllCovData(){
        logger.info("get all data called");
        return covDataService.getAllCovData();
    }

    @GetMapping(path="/getByPays")
    public List<CovData> getByPays(@RequestParam("pays") String pays){
        logger.info("get country data called");
        return covDataService.getByPays(pays);
    }

    @GetMapping(path="/getByPaysAndDate")
    public CovData getByPaysAndDate(@RequestParam("pays") String pays, @RequestParam("date") String date){
        logger.info("get country data by date called");
        return covDataService.getByPaysAndDate(pays, LocalDate.parse(date));
    }

    @GetMapping(path="/getByPaysAndDateNonCumule")
    public CovData getByPaysAndDateNonCumule(@RequestParam("pays") String pays, @RequestParam("date") String date){
        logger.info("get country data by date called");
        return covDataService.getByPaysAndDateNonCumule(pays, LocalDate.parse(date));
    }

    @GetMapping(path="/getByPaysToday")
    public CovData getByPaysToday(@RequestParam("pays") String pays){
        logger.info("get today's country data called");
        return covDataService.getByPaysToday(pays);
    }
}
