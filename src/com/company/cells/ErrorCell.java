package com.company.cells;


public class ErrorCell extends Cell {
    public ErrorCell(String coordinateXY) {
        super(coordinateXY, "#");
    }

    public ErrorCell(String coordinateXY, String description) {
        super(coordinateXY, "# "+ description);
    }


    @Override
    public void processValue() {

        setValue(getRawValue());
        setIsProcessed(true);
    }
}
