package ru.msu.school.siburandug;

import android.content.Context;
import android.content.res.Resources;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Олег on 20.02.2018.
 */

public class GameButton extends android.support.v7.widget.AppCompatButton{
    String id;

    public GameButton(Context context) {
        super(context);
    }

    public GameButton (Context context, LinearLayout layout, int i, Resources resources) {
        super(context);
        id = (resources.getStringArray(R.array.ButtonsIds))[i];
        setText(id);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        layout.addView(this, layoutParams);

    }
}
