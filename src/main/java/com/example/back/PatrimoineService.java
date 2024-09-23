package com.example.back;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PatrimoineService {

        private ObjectMapper objectMapper;

        public PatrimoineService() {
            this.objectMapper = new ObjectMapper();
        }

    public void saveToFile(Patrimoine patrimoine) throws IOException {
        int id = ThreadLocalRandom.current().nextInt(1, 10000);

        String str = objectMapper.writeValueAsString(patrimoine);

        String lineToWrite = id + ": " + str;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("patrimoineFiles.txt", true))) {
            writer.append(lineToWrite);
            writer.newLine();
        }
    }
}
