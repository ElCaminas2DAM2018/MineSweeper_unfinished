package org.ieselcaminas.victor.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static android.R.attr.button;
import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    private MineButton[][] board;
    private BombMatrix bombMatrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bombMatrix = new BombMatrix();

        board = new MineButton[Singleton.sharedInstance().getNumRows()]
                              [Singleton.sharedInstance().getNumCols()];

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        gridLayout.setRowCount(Singleton.sharedInstance().getNumRows());
        gridLayout.setColumnCount(Singleton.sharedInstance().getNumCols());



        for (int i=0; i<Singleton.sharedInstance().getNumRows(); i++) {
            for (int j=0; j<Singleton.sharedInstance().getNumCols(); j++) {

                board[i][j] = new MineButton(getApplicationContext(), i, j);
                //board[i][j].setAdjustViewBounds(true);

                FrameLayout frameLayout = new FrameLayout(this);
                BackCell backCell = new BackCell(this);
                frameLayout.addView(backCell);
                if (bombMatrix.getValue(i,j) == -1) {
                    BombView bomb = new BombView(this);
                    frameLayout.addView(bomb);
                    board[i][j].setAlpha(0.5f);

                } else {
                    frameLayout.addView(bombMatrix.getTextView(this, i, j));
                }
                frameLayout.addView(board[i][j]);
                gridLayout.addView(frameLayout);

                board[i][j].setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        MineButton mineButton = (MineButton) view;
                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            mineButton.setBackgroundDrawable(getResources().
                                    getDrawable(R.drawable.boton_pressed));
                        } else {
                            mineButton.setBackgroundDrawable(getResources().
                                    getDrawable(R.drawable.boton));
                        }
                        return false;
                    }
                });

                board[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MineButton mineButton = (MineButton) view;

                        int row = mineButton.getRow();
                        int col = mineButton.getCol();
                        if (bombMatrix.getValue(row,col) == -1) {
                            //performGameOver(row, col);
                        } else {

                            if (bombMatrix.getValue(row,col) == 0) {
                                open(row,col);
                            } else {
                                mineButton.setVisibility(View.INVISIBLE);
                                mineButton.setState(ButtonState.OPEN);
                            }
                        }
                    }
                });

                board[i][j].setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        MineButton button = (MineButton) v;
                        ButtonState state = button.getState();
                        switch (state) {
                            case CLOSED:
                                button.setImageDrawable(getResources().
                                        getDrawable(R.drawable.flag));
                                button.setState(ButtonState.FLAG);
                                break;
                            case FLAG:
                                button.setImageDrawable(getResources().
                                        getDrawable(R.drawable.question));
                                button.setState(ButtonState.QUESTION);
                                break;
                            case QUESTION:
                                button.setImageDrawable(null);
                                button.setState(ButtonState.CLOSED);
                                break;
                        }

                        return true;
                    }
                });
            }

        }

    }

    public void open(int row, int col) {
        if ((row>=0) && (row<Singleton.sharedInstance().getNumRows()
            && (col>=0) && (col<Singleton.sharedInstance().getNumCols()))) {
                if ((bombMatrix.getValue(row,col) == 0) &&
                        (board[row][col].getState() == ButtonState.CLOSED)){
                    board[row][col].setVisibility(View.INVISIBLE);
                    board[row][col].setState(ButtonState.OPEN);
                    for (int i=-1; i<=1; i++) {
                        open(row-1,col+i);
                    }
                    open(row, col-1);
                    open(row, col+1);
                    for (int i=-1; i<=1; i++) {
                        open(row+1,col+i);
                    }
                } else {
                    if (bombMatrix.getValue(row,col) != -1) {
                        board[row][col].setVisibility(View.INVISIBLE);
                        board[row][col].setState(ButtonState.OPEN);
                    }
                }
        }
    }
}
