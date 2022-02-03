package com.covstats.controllers;

import com.covstats.entities.CovData;
import com.covstats.services.CovDataService;
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

    public CovDataController(CovDataService covDataService) {
        this.covDataService = covDataService;
    }

    @GetMapping()
    public List<CovData> getAllCovData(){
        return covDataService.getAllCovData();
    }

    @GetMapping(path="/getByPays")
    public List<CovData> getByPays(@RequestParam("pays") String pays){
        return covDataService.getByPays(pays);
    }

    @GetMapping(path="/getByPaysAndDate")
    public CovData getByPaysAndDate(@RequestParam("pays") String pays, @RequestParam("date") String date){
        return covDataService.getByPaysAndDate(pays, LocalDate.parse(date));
    }

    @GetMapping(path="/getByPaysToday")
    public CovData getByPaysAndDate(@RequestParam("pays") String pays){
        return covDataService.getByPaysToday(pays);
    }
}
