package com.covstats.Helpers;

import com.covstats.entities.CovData;
import org.apache.commons.csv.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERS = { "Date", "Pays", "Infections", "Deces", "Guerisons", "TauxDeces", "TauxGuerison", "TauxInfection" };

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<CovData> csvToCovData(URL url) {
        InputStream is;
        try {
            URLConnection yc = url.openConnection();
            is = yc.getInputStream();
        } catch(IOException e){
            e.getMessage();
            return null;
        }
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is));

             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<CovData> covDataList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords){
                if(csvRecord.getRecordNumber() > 7){
                CovData covData = new CovData(
                        csvParser.getRecordNumber(),
                        LocalDate.parse(csvRecord.get(0)),
                        csvRecord.get(1),
                        Long.parseLong(csvRecord.get(2)),
                        Long.parseLong(csvRecord.get(3)),
                        Long.parseLong(csvRecord.get(4)),
                        Double.parseDouble(csvRecord.get(5)),
                        Double.parseDouble(csvRecord.get(6)),
                        Double.parseDouble(csvRecord.get(7))
                );

                covDataList.add(covData);
            }}
            return covDataList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }



}
