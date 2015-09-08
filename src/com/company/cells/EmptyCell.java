package com.company.cells;


public class EmptyCell extends Cell {
    public EmptyCell(String coordinateXY) {
        super(coordinateXY, "");
    }

    @Override
    public void processValue() {

        setValue(getRawValue());
        setIsProcessed(true);
    }
}
