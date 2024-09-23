package com.example.back;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class PatrimoineService {

        private ObjectMapper objectMapper;

        public PatrimoineService() {
            this.objectMapper = new ObjectMapper();
        }

    public void saveToFile(Patrimoine patrimoine) throws IOException {
        String str = objectMapper.writeValueAsString(patrimoine);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("patrimoineFiles.txt", true))) {
            writer.append(str);
            writer.newLine();
        }
    }

    public static void main(String[] args) throws IOException{
        PatrimoineService patrimoineService=new PatrimoineService();
        Patrimoine patrimoine=new Patrimoine("Narindra", DateTimeFormatter.ISO_LOCAL_DATE);
        patrimoineService.saveToFile(patrimoine);
    }
}
