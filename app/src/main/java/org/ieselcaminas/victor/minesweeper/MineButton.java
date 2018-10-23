package org.ieselcaminas.victor.minesweeper;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by vmalonso on 21/10/16.
 */
public class MineButton extends android.support.v7.widget.AppCompatImageButton {

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    private int col, row;
    private ButtonState state;

    public MineButton(Context c, int row, int col) {
        super(c);
        this.row = row;
        this.col = col;
        state = ButtonState.CLOSED;

        final float scale = getContext().getResources().
                getDisplayMetrics().density;
        int width = (int)(WIDTH * scale);
        int height = (int)(HEIGHT * scale);

        android.view.ViewGroup.LayoutParams params = new
                    FrameLayout.LayoutParams(width,height);
        setLayoutParams(params);

        setBackgroundDrawable(getResources().getDrawable(R.drawable.boton));
        setScaleType(ScaleType.FIT_XY); // CENTER_CROP
        setPadding((int)(4*scale),(int)(4*scale),(int)(4*scale),(int)(4*scale));
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public ButtonState getState() {
        return state;
    }

    public void setState(ButtonState state) {
        this.state = state;
    }

}
