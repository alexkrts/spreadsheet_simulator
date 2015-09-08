package com.company.cells;

import com.company.CellFactory;

import java.util.Map;


public class ExpressionCell extends Cell {
    private Map<String, Cell> cellsMap;

    public ExpressionCell(String coordinateXY, String value, Map<String, Cell> cellsMap) {
        super(coordinateXY, value);
        this.cellsMap = cellsMap;
    }


    @Override
    public void processValue() {
        String rawValue = getRawValue().substring(1);
        if (rawValue.matches(CellFactory.CELL_CONTAINS_ONLY_ADDRESS_PATTERN)) {
            Cell cell = cellsMap.get(rawValue);
            if (cell != null) {
                setValue(cell.getValue());
            } else {
                setValue(CellFactory.CELL_ERROR_VALUE + " Cell link not found");
            }
        } else if (rawValue.matches(CellFactory.CELL_CONTAINS_NOT_ONLY_ADDRESS_PATTERN)) {


            String[] operands = rawValue.replaceAll("[-,+,*,/]{1}", " ").split(" ");
            String[] operators = rawValue.replaceAll("[A-Za-z0-9.]+", " ").trim().split(" ");
            for (int i = 0; i < operands.length; i++) {
                if (operands[i].matches(CellFactory.CELL_CONTAINS_ONLY_ADDRESS_PATTERN)) {
                    if (cellsMap.get(operands[i]) != null) {
                        operands[i] = cellsMap.get(operands[i]).getValue();
                    } else {
                        setValue(CellFactory.CELL_ERROR_VALUE + " Cell link not found");
                    }
                }

            }
            String expValue = expressionProcessing(operands, operators);

            setValue(expValue);
        } else {
            setValue(CellFactory.CELL_ERROR_VALUE + " Incorrect cell value. Doesn't match any pattern");
        }
        setIsProcessed(true);
    }


    public String expressionProcessing(String[] operands, String[] operators) {

        double value;
        if (!operands[0].matches(CellFactory.CELL_NUMBER_PATTERN)) {
            return (CellFactory.CELL_ERROR_VALUE + " Expression failure");
        }
        value = Double.parseDouble(operands[0]);
        for (int i = 0; i < operators.length; i++) {

            String operator = operators[i];
            int operandsIndex = i + 1;
            if (!operands[operandsIndex].matches(CellFactory.CELL_NUMBER_PATTERN)) {
                return (CellFactory.CELL_ERROR_VALUE + " Expression failure");
            }
            switch (operator) {

                case ("+"): {
                    value = value + Double.parseDouble(operands[operandsIndex]);

                    break;
                }
                case ("-"): {
                    value = value - Double.parseDouble(operands[operandsIndex]);

                    break;
                }
                case ("*"): {
                    value = value * Double.parseDouble(operands[operandsIndex]);

                    break;
                }
                case ("/"): {
                    value = value / Double.parseDouble(operands[operandsIndex]);

                    break;
                }
                default: {
                    return (CellFactory.CELL_ERROR_VALUE + "Expression failure");

                }


            }


        }

        return Double.toString(value);
    }

}
