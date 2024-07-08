package com.example.trading_project;
import com.opencsv.*;

import java.io.FileWriter;
import java.io.IOException;

public class ExportData {

    public static CSVWriter writer;

    static {
        try {
            writer = new CSVWriter(new FileWriter(""));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeInCSV(String[][] line) {
        for (String[] s : line)
            writer.writeNext(s);

        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
