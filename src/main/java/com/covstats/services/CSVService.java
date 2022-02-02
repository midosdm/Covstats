package com.covstats.services;

import com.covstats.Helpers.CSVHelper;
import com.covstats.Repositories.CovDataRepository;
import com.covstats.entities.CovData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    private CovDataRepository covDataRepository;

    public final URL url = new URL("https://coronavirus.politologue.com/data/coronavirus/coronacsv.aspx?format=csv&t=pays");

    public CSVService() throws MalformedURLException {
    }

    public void save(URL url) {
            List<CovData> covDataList = CSVHelper.csvToCovData(url);
            covDataList.stream().forEach((covData -> covDataRepository.save(covData)));
    }

    @Scheduled(cron = "0 15 * * * ?")
    public void callBack(){
        save(url);
    }
    public List<CovData> getAllTutorials() {
        return covDataRepository.findAll();
    }
}
