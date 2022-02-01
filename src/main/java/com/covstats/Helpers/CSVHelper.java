package com.covstats.Helpers;

import com.covstats.entities.CovData;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };

    public static boolean hasCSVFormat(MultipartFile file) {
        if (TYPE.equals(file.getContentType())
                || file.getContentType().equals("application/vnd.ms-excel")) {
            return true;
        }

        return false;
    }

    public static List<CovData> csvToCovData(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<CovData> covDataList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                CovData covData = new CovData(
                        csvParser.getRecordNumber(),
                        LocalDate.parse(csvRecord.get("Date")),
                        csvRecord.get("Pays"),
                        Long.parseLong(csvRecord.get("Infections")),
                        Long.parseLong(csvRecord.get("Deces")),
                        Long.parseLong(csvRecord.get("Guerisons")),
                        Double.parseDouble(csvRecord.get("TauxDeces")),
                        Double.parseDouble(csvRecord.get("TauxGuerison")),
                        Double.parseDouble(csvRecord.get("TauxInfection"))
                );

                covDataList.add(covData);
            }

            return covDataList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
