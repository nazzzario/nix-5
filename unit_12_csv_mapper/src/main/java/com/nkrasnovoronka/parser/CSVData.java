package com.nkrasnovoronka.parser;

import com.nkrasnovoronka.util.CSVFileReader;

import java.util.*;
import java.util.stream.Collectors;

public class CSVData {
    private final List<String> header;
    private final List<String[]> cells;
    private final Map<String, Integer> columnIndexes;


    public CSVData(String filePath) {
        List<String[]> strings = CSVFileReader.readCsvFile(filePath);
        header = Arrays.asList(strings.get(0));
        cells = strings.stream().skip(1).collect(Collectors.toList());
        columnIndexes = createIndexMap();
    }

    public List<String> getHeader() {
        return header;
    }

    public List<String[]> getCells() {
        return cells;
    }

    public String getCell(int row, int col) {
        checkIndex(row, col);
        String[] strings = cells.get(row);
        return strings[col];
    }

    public String getCell(int row, String headerName) {
        int cell = columnIndexes.get(headerName);
        return getCell(row, cell);
    }

    public int getRows(){
        return cells.size();
    }

    public int getColumns(){
        return header.size();
    }

    private void checkIndex(int row, int col) {
        if (row < 0 || col < 0) {
            throw new RuntimeException("Invalid index");
        }
    }

    private Map<String, Integer> createIndexMap(){
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < header.size(); i++) {
            indexMap.put(header.get(i), i);
        }
        return indexMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CSVData)) return false;
        CSVData csvData = (CSVData) o;
        return Objects.equals(header, csvData.header) && Objects.equals(cells, csvData.cells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, cells);
    }

    @Override
    public String toString() {
        return "CSVParser{" +
                "header=" + header +
                ", cells=" + cells +
                '}';
    }
}
