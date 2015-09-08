package com.company.cells;


public abstract class Cell {
    private String coordinateXY;
    private String value;
    private String rawValue;
    private boolean isProcessed = false;


    public String getRawValue() {
        return rawValue;
    }

    public Cell(String coordinateXY, String rawValue) {
        this.coordinateXY = coordinateXY;
        this.rawValue = rawValue;
    }


    public abstract void processValue();

    public String getValue() {
        if (!isProcessed) {
            processValue();
        }
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public String getCoordinateXY() {
        return coordinateXY;
    }


    public boolean isProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(boolean isProcessed) {
        this.isProcessed = isProcessed;
    }

}
