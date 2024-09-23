package com.example.back;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PatrimoineService {

        private final ObjectMapper objectMapper = new ObjectMapper();
        private final String filePath = "patrimoineFiles.txt";

        public Map<Integer, Patrimoine> loadFromFile() throws IOException {
            Map<Integer, Patrimoine> patrimoines = new HashMap<>();

            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(": ", 2);
                int id = Integer.parseInt(parts[0]);
                Patrimoine patrimoine = objectMapper.readValue(parts[1], Patrimoine.class);

                patrimoines.put(id, patrimoine);
            }

            return patrimoines;
        }

        public void saveToFile(int id, Patrimoine patrimoine) throws IOException {
            Map<Integer, Patrimoine> patrimoines = loadFromFile();

            patrimoines.put(id, patrimoine);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
                for (Map.Entry<Integer, Patrimoine> entry : patrimoines.entrySet()) {
                    String lineToWrite = entry.getKey() + ": " + objectMapper.writeValueAsString(entry.getValue());
                    writer.append(lineToWrite);
                    writer.newLine();
                }
            }
        }

        public Patrimoine getPatrimoineById(int id) throws IOException {
            Map<Integer, Patrimoine> patrimoines = loadFromFile();
            return patrimoines.get(id);
        }
    }

