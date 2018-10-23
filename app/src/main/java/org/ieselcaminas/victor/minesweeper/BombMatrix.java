package org.ieselcaminas.victor.minesweeper;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.widget.TextView;

import java.util.Random;

import static android.os.Build.VERSION_CODES.N;

/**
 * Created by vmalonso on 21/10/16.
 */
public class BombMatrix {

    private int[][] bombMatrix;

    public BombMatrix() {
        bombMatrix = new int[Singleton.sharedInstance().getNumRows()]
                            [Singleton.sharedInstance().getNumCols()];
        resetMatrix();
    }

    private boolean isBombNext(int row, int col) {
        if (row>=0 && row<Singleton.sharedInstance().getNumRows() &&
                col>=0 && col<Singleton.sharedInstance().getNumCols()) {
            if (bombMatrix[row][col] == -1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void resetMatrix() {
        for (int i=0; i<Singleton.sharedInstance().getNumRows(); i++) {
            for (int j=0; j<Singleton.sharedInstance().getNumCols(); j++) {
                bombMatrix[i][j] = 0;
            }
        }
        int numBombs = 0;
        int row, col;
        Random random = new Random();
        while (numBombs<Singleton.sharedInstance().getNumBombs()) {
            row = random.nextInt(Singleton.sharedInstance().getNumRows());
            col = random.nextInt(Singleton.sharedInstance().getNumCols());
            if (bombMatrix[row][col] != -1) {
                bombMatrix[row][col] = -1;
                numBombs++;
            }
        }
        for (int i=0; i<Singleton.sharedInstance().getNumRows(); i++) {
            for (int j = 0; j < Singleton.sharedInstance().getNumCols(); j++) {
                numBombs = 0;
                if (bombMatrix[i][j] != -1) {
                        if (isBombNext(i-1,j-1)) numBombs ++;
                        if (isBombNext(i-1,j)) numBombs ++;
                        if (isBombNext(i-1,j+1)) numBombs ++;
                        if (isBombNext(i,j-1)) numBombs ++;
                        if (isBombNext(i,j+1)) numBombs ++;
                        if (isBombNext(i+1,j-1)) numBombs ++;
                        if (isBombNext(i+1,j)) numBombs ++;
                        if (isBombNext(i+1,j+1)) numBombs ++;
                        bombMatrix[i][j] = numBombs;
                }

               /* if (bombMatrix[i][j] != -1) {
                    int count = 0;
                    if (i-1>=0) {
                        if (j-1>=0) {
                            if (bombMatrix[i-1][j-1] == -1) {
                                count ++;
                            }
                        }
                        if (bombMatrix[i - 1][j] == -1) {
                            count++;
                        }
                        if (j+1 < Singleton.sharedInstance().getNumCols()) {
                            if (bombMatrix[i - 1][j+1] == -1) {
                                count++;
                            }
                        }
                    }
                    if (j-1 >= 0) {
                        if (bombMatrix[i][j-1] == -1) {
                            count++;
                        }
                    }
                    if (j+1 < Singleton.sharedInstance().getNumCols()) {
                        if (bombMatrix[i][j+1] == -1) {
                            count++;
                        }
                    }
                    if (i+1 < Singleton.sharedInstance().getNumRows()) {
                        if (j-1>=0) {
                            if (bombMatrix[i+1][j-1] == -1) {
                                count ++;
                            }
                        }
                        if (bombMatrix[i+1][j] == -1) {
                            count++;
                        }
                        if (j+1 < Singleton.sharedInstance().getNumCols()) {
                            if (bombMatrix[i+1][j+1] == -1) {
                                count++;
                            }
                        }
                    }
                    bombMatrix[i][j] = count;
                } */
            }
        }

        for (int i=0; i<Singleton.sharedInstance().getNumRows(); i++) {
            for (int j=0; j<Singleton.sharedInstance().getNumCols(); j++) {
                System.out.print(bombMatrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public TextView getTextView(Context c, int row, int col) {
        TextView text = new TextView(c);
        float scale = c.getResources().
                getDisplayMetrics().density;
        text.setPadding((int) (scale * 10),
                (int)(scale*3),
                (int)(scale*5),
                (int)(scale*5));
        int n = bombMatrix[row][col];
        switch (n) {
            case 1: text.setTextColor(Color.BLUE);
                break;
            case 2: text.setTextColor(Color.GREEN);
                break;
            case 3: text.setTextColor(Color.RED);
                break;
            case 4: text.setTextColor(Color.CYAN);
                break;
            case 5: text.setTextColor(Color.YELLOW);
                break;
            case 6: text.setTextColor(Color.MAGENTA);
                break;
            case 7: text.setTextColor(Color.GRAY);
                break;
            case 8: text.setTextColor(Color.DKGRAY);
                break;
        }
        if ((n!=0) && (n!=-1))
            text.setText(Html.fromHtml("<b>"+n+"</b>"));

        return text;
    }

    public int getValue(int row, int col) {
        return bombMatrix[row][col];
    }

}
