package com.company.cells;


public class TextCell extends Cell {
    public TextCell(String coordinateXY, String value) {
        super(coordinateXY, value);

    }

    @Override
    public void processValue() {
        setValue(getRawValue().substring(1));
        setIsProcessed(true);

    }
}
