package com.company;

import com.company.cells.*;

import java.util.Map;

public class CellFactory {

    public static final String CELL_TEXT_PATTERN = "^\\'.+";
    public static final String CELL_EXPRESSION_PATTERN = "^\\=.+";
    public static final String CELL_NUMBER_PATTERN = "^[+-]?(?:\\d+\\.?\\d*|\\d*\\.?\\d+)[\\r\\n]*$";
    public static final String CELL_CONTAINS_ONLY_ADDRESS_PATTERN = "^([A-Z]+[0-9]+)$";
    public static final String CELL_CONTAINS_NOT_ONLY_ADDRESS_PATTERN = ".+[+|\\-|*|/].+";
    public static final String CELL_ERROR_VALUE = "#";
    private Map<String, Cell> cellsMap;

    public CellFactory(Map<String, Cell> cellsMap) {
        this.cellsMap = cellsMap;
    }

    public Cell createCell(String key, String value) {


        if (value.matches(CELL_TEXT_PATTERN)) {
            TextCell textCell = new TextCell(key, value);

            return textCell;

        } else if (value.matches(CELL_NUMBER_PATTERN)) {
            NumberCell numberCell = new NumberCell(key, value);

            return numberCell;

        } else if (value.matches(CELL_EXPRESSION_PATTERN)) {
            ExpressionCell expressionCell = new ExpressionCell(key, value, cellsMap);

            return expressionCell;

        } else if (value.equals("")) {
            EmptyCell emptyCell = new EmptyCell(key);
            return emptyCell;

        } else {
            ErrorCell errorCell = new ErrorCell(key, "Unknown cell format");
            return errorCell;

        }

    }


}





