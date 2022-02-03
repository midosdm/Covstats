package com.covstats.services;

import com.covstats.Helpers.CSVHelper;
import com.covstats.Repositories.CovDataRepository;
import com.covstats.entities.CovData;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CovDataService {
    @Autowired
    private CovDataRepository covDataRepository;

    public final URL url = new URL("https://coronavirus.politologue.com/data/coronavirus/coronacsv.aspx?format=csv&t=pays");

    public CovDataService() throws MalformedURLException {
    }

    public void save(URL url) {
            List<CovData> covDataList = CSVHelper.csvToCovData(url);
            covDataList.stream().forEach((covData -> covDataRepository.save(covData)));
        System.out.println("save called");
    }

    @Scheduled(cron = "* * 1 * * ?")
    public void callBack(){
        save(url);
    }

    public List<CovData> getAllCovData() {
        return covDataRepository.findAll();
    }

    public List<CovData> getByPays(String pays){
        try{
//            List<CovData> covDataList = covDataRepository.findByPays(pays);
            List<CovData> covDataList = new ArrayList<CovData>();
            covDataRepository.findByPays(pays).forEach(covDataList::add);

            return covDataList;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public CovData getByPaysAndDate(String pays, LocalDate date){
        try{
            CovData covData = covDataRepository.findByPaysAndDate(pays, date);
            return covData;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public CovData getByPaysToday(String pays){

        try{
            LocalDate today = LocalDate.now();
            CovData covDataYesterday = covDataRepository.findByPaysAndDate(pays, today.minusDays(1));
            CovData covDataToday = covDataRepository.findByPaysAndDate(pays,today);

            CovData result = new CovData();
            result.setId(covDataToday.getId());
            result.setPays(pays);
            result.setDate(today);
            result.setDeces(covDataToday.getDeces() - covDataYesterday.getDeces());
            result.setInfections(covDataToday.getInfections() - covDataYesterday.getInfections());
            result.setGuerisons(covDataToday.getGuerisons() - covDataYesterday.getGuerisons());
            result.setTauxDeces(covDataToday.getTauxDeces());
            result.setTauxGuerison(covDataToday.getTauxGuerison());
            result.setTauxInfection(covDataToday.getTauxInfection());
            System.out.println(result);
            return result;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
