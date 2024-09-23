package com.example.back;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PatrimoineTest {
    @Test
    public void givenWritingStringToFile_whenUsingPrintWriter_thenCorrect()throws IOException {
        String fileName="file";
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("Some String");
        printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000);
        printWriter.close();
    }
}

