package br.utfpr.edu;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.*;

public class Main {

    private static final String INPUT_CSV_FILE = "D:\\USERDATA\\IdeaProjects\\csv\\src\\main\\java\\br\\utfpr\\edu\\povoseterras.csv";
    private static final String OUTPUT_CSV_FILE  = "D:\\USERDATA\\IdeaProjects\\csv\\src\\main\\java\\br\\utfpr\\edu\\saida.csv";

    public static void main(String[] args) {

        List<TesteRel> tests = readCSVRelacionado(INPUT_CSV_FILE);
        writeCSVRelacionado(OUTPUT_CSV_FILE, tests);
    }

    public static List<TesteRel> readCSVRelacionado(String filePath) {
        Set<TesteRel> testsSet = new HashSet<>();
        List<TesteRel> tests = new ArrayList<>();

        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(parser)
                .build()) {
            List<String[]> records = reader.readAll();
            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);
                if (record.length == 4) {
                    String[] pais = record[2].split(",\\s*");
                    String[] relacionados = record[3].split(",\\s*");
                    for (String pai : pais) {
                        for (String relacionado : relacionados) {
                            TesteRel teste = new TesteRel(pai, relacionado);
                            testsSet.add(teste);
                        }
                    }
                }
            }
            tests = new ArrayList<>(testsSet);
            Collator collator = Collator.getInstance(Locale.getDefault());
            collator.setStrength(Collator.PRIMARY);
            tests.sort(Comparator.comparing((TesteRel t) -> t.getPai(), collator)
                    .thenComparing((TesteRel t) -> t.getRelacionado(), collator));

            for (int i = 0; i < tests.size(); i++) {
                tests.get(i).setIdfixo(i + 1);
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public static void writeCSVRelacionado(String filePath, List<TesteRel> tests) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath), ';', CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
            String[] header = {"idfixo", "pai", "relacao"};
            writer.writeNext(header);

            for (TesteRel test : tests) {
                String[] record = {
                        String.valueOf(test.getIdfixo()),
                        test.getPai(),
                        test.getRelacionado()
                };
                writer.writeNext(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}