package org.ieselcaminas.victor.minesweeper;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static org.ieselcaminas.victor.minesweeper.R.drawable.bomb;

/**
 * Created by victor on 03/11/16.
 */

public class BombView extends ImageView {
    public BombView(Context c) {
        super(c);
        setImageDrawable(getResources().
                getDrawable(bomb));
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.
                LayoutParams(MineButton.WIDTH,
                        MineButton.HEIGHT);
        setLayoutParams(layoutParams);
    }
}
