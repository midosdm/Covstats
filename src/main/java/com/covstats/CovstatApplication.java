package com.covstats;

import com.covstats.services.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.MalformedURLException;
import java.net.URL;

//implements CommandLineRunner
@SpringBootApplication
@EnableScheduling
public class CovstatApplication {
//    @Autowired
//    private CSVService csvService;
    public static void main(String[] args) {
        SpringApplication.run(CovstatApplication.class, args);
    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        try {
//            URL url = new URL("https://coronavirus.politologue.com/data/coronavirus/coronacsv.aspx?format=csv&t=pays");
//            csvService.save(url);
//        }catch(MalformedURLException mue){
//            mue.getMessage();
//        }
//    }
}
