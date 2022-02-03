package com.covstats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

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
