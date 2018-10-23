package org.ieselcaminas.victor.minesweeper;

/**
 * Created by vmalonso on 21/10/16.
 */
public class Singleton {

    private static Singleton sharedInstance;
    private int numRows, numCols;
    private int numBombs;

    private Singleton() {
        numCols = 30;
        numRows = 30;
        numBombs = 50;
    }

    public static Singleton sharedInstance() {
        if (sharedInstance == null)
            sharedInstance = new Singleton();
        return sharedInstance;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public int getNumBombs() {
        return numBombs;
    }

    public void setNumBombs(int numBombs) {
        this.numBombs = numBombs;
    }
}
