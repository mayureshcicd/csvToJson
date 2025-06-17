package com.json.demo.controller;

import com.json.util.CsvToJsonConverter;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@AllArgsConstructor
@RequestMapping("api/")
public class JsonConverter {
   private final CsvToJsonConverter converter;


    @PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addPlatform(
            HttpServletRequest request,
            @Parameter(
                    description = "Upload CSV file",
                    required = true,
                    schema = @Schema(type = "string", format = "binary")
            )
            @RequestPart("csvFile") MultipartFile csvFile) {

        String jsonString = converter.convertCsvToJson(csvFile);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonString);
    }
}


