package com.json.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.*;

@Component
public class CsvToJsonConverter {

    private static final Logger log = LoggerFactory.getLogger(CsvToJsonConverter.class);

    /**
     * Converts MultipartFile (.csv) directly to pretty JSON string.
     */
    public String convertCsvToJson(MultipartFile csvFile) {
        if (csvFile == null || csvFile.isEmpty()) {
            throw new IllegalArgumentException("CSV file is required.");
        }

        List<Map<String, String>> dataList = new ArrayList<>();

        try (
                InputStreamReader reader = new InputStreamReader(csvFile.getInputStream());
                CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(reader)
        ) {
            Map<String, String> row;
            while ((row = csvReader.readMap()) != null) {
                dataList.add(row);
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String jsonOutput = mapper.writeValueAsString(dataList);

            log.info("CSV successfully converted to JSON string.");
            return jsonOutput;

        } catch (CsvValidationException e) {
            log.error("CSV parsing failed: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to parse CSV file", e);
        } catch (Exception e) {
            log.error("Unexpected error while converting CSV to JSON: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to convert CSV to JSON", e);
        }
    }
}
