package com.company.cells;


public class NumberCell extends Cell {
    public NumberCell(String coordinateXY, String value) {
        super(coordinateXY, value);
    }

    @Override
    public void processValue() {

        setValue(getRawValue());
        setIsProcessed(true);
    }
}
