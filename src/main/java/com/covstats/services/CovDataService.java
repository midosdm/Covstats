package com.covstats.services;

import com.covstats.helpers.CSVHelper;
import com.covstats.repositories.CovDataRepository;
import com.covstats.entities.CovData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(CovDataService.class);

    public final URL url = new URL("https://coronavirus.politologue.com/data/coronavirus/coronacsv.aspx?format=csv&t=pays");

    // this is a simple constructor stating that this class throws a malformedUrlException
    public CovDataService() throws MalformedURLException {
    }

    /**
     *
     * @param url
     */
    public void save(URL url) {
            List<CovData> covDataList = CSVHelper.csvToCovData(url);
            covDataList.stream().forEach((covData -> covDataRepository.save(covData)));
            logger.debug("save called");
    }

    /**
     *
     */
    @Scheduled(cron = "* * 1 * * ?")
    public void callBack(){
        save(url);
    }

    public List<CovData> getAllCovData() {
        return covDataRepository.findAll();
    }

    public List<CovData> getByPays(String pays){
        List<CovData> covDataList = new ArrayList<>();
        try{
            covDataRepository.findByPays(pays).forEach(covDataList::add);

            return covDataList;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return covDataList;
    }

    /**
     *
     * @param pays
     * @param date
     * @return
     */
    public CovData getByPaysAndDate(String pays, LocalDate date){
        try{
            return covDataRepository.findByPaysAndDate(pays, date);
        }catch (Exception e){
            logger.error(e.getMessage());
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

            logger.info(result);
            return result;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
}
