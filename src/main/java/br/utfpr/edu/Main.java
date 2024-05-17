package br.utfpr.edu;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String INPUT_CSV_FILE = "D:\\USERDATA\\IdeaProjects\\csv\\src\\main\\java\\br\\utfpr\\edu\\entrada.csv";
    private static final String OUTPUT_CSV_FILE  = "D:\\USERDATA\\IdeaProjects\\csv\\src\\main\\java\\br\\utfpr\\edu\\saida.csv";

    public static void main(String[] args) {
        List<Teste> tests = readCSV(INPUT_CSV_FILE);
        writeCSV(OUTPUT_CSV_FILE, tests);
    }

    public static List<Teste> readCSV(String filePath) {
        List<Teste> tests = new ArrayList<>();

        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(parser)
                .build()) {
            List<String[]> records = reader.readAll();
            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);
                if (record.length == 4) {
                    int idfixo = i;
                    String situacao = "ATIVO";
                    String[] terras = record[3].split(",\\s*");
                    for (String terra : terras) {
                        Teste teste = new Teste(idfixo, terra, situacao);
                        tests.add(teste);
                        idfixo++;
                    }
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public static void writeCSV(String filePath, List<Teste> tests) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath), ';', CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
            String[] header = {"idfixo", "descricao", "situacao"};
            writer.writeNext(header);

            for (Teste test : tests) {
                String[] record = {
                        String.valueOf(test.getIdfixo()),
                        test.getDescricao(),
                        test.getSituacao()
                };
                writer.writeNext(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}