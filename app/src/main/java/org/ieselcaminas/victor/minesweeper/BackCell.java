package org.ieselcaminas.victor.minesweeper;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by vmalonso on 21/10/16.
 */
public class BackCell extends android.support.v7.widget.AppCompatImageView {

    public BackCell(Context context) {
        super(context);
        setImageDrawable(getResources().getDrawable(R.drawable.back));
        final float scale = getContext().getResources().
                getDisplayMetrics().density;
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(
                        (int)(MineButton.WIDTH * scale),
                        (int) (scale * MineButton.HEIGHT));
        setLayoutParams(layoutParams);
    }
}
