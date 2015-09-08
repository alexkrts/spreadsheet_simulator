package com.company;

import com.company.cells.Cell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SpreadSheet {
    private final String INPUT_FILE = "src/com/company/spreadsheet_source.txt";
    private Map<String, Cell> cellsMap = new HashMap<String, Cell>();
    private int columnsCount = 0;
    private int rowsCount = 0;
    ColumnKeyGenerator generator = new ColumnKeyGenerator();


    public Map createCells() {

        CellFactory cellFactory = new CellFactory(cellsMap);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(INPUT_FILE));
            String row;
            int currentRow = 1;
            int currentColumn = 0;
            while ((row = reader.readLine()) != null) {
                String cellsArray[] = row.split("\t");
                if (cellsArray.length > columnsCount) {
                    columnsCount = cellsArray.length;
                }
                rowsCount = currentRow;
                Cell cell;

                for (String value : cellsArray) {
                    String keyGen = generator.convertKeyToString(currentColumn);
                    String key = keyGen + currentRow;
                    cell = cellFactory.createCell(key, value);
                    cellsMap.put(cell.getCoordinateXY(), cell);
                    currentColumn++;
                }
                currentColumn = 0;
                currentRow++;
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cellsMap;
    }


    public void printCells() {
        String key;
        for (int rowPos = 1; rowPos <= rowsCount; rowPos++) {
            for (int i = 0; i < columnsCount; i++) {
                key = generator.convertKeyToString(i) + rowPos;
                if (cellsMap.containsKey(key)) {
                    Cell cell = cellsMap.get(key);
                    String cellValue = cell.getValue(); //cells processing here
                    System.out.print(key + "[" + cellValue + "]   ");

                }
            }
            System.out.println("");

        }
    }


    public int getColumnsCount() {
        return columnsCount;
    }

    public void setColumnsCount(int columnsCount) {
        this.columnsCount = columnsCount;
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public void setRowsCount(int rowsCount) {
        this.rowsCount = rowsCount;
    }

}
