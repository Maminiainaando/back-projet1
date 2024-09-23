package com.example.back;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PatrimoineTest {

    private static final String FILE_PATH = "patrimoineFiles.txt";

    @Test
    public void testNoDuplicates() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));

        Set<Integer> ids = new HashSet<>();
        int duplicateCount = 0;

        for (String line : lines) {
            String[] parts = line.split(": ", 2);

            if (parts.length == 2 && !parts[0].trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    if (!ids.add(id)) {
                        duplicateCount++;
                    }
                } catch (NumberFormatException e) {
                    duplicateCount++;
                }
            }
        }

        assertEquals(0, duplicateCount);
    }

    @Test
    public void testNoMissingValues() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));

        boolean hasEmptyStrings = false;

        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                String[] parts = line.split(": ", 2);

                if (parts.length != 2 || parts[1].trim().isEmpty()) {
                    hasEmptyStrings = true;
                    break;
                }
            }
        }

        assertFalse(hasEmptyStrings);
    }
}
