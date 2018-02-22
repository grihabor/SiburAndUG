package ru.msu.school.siburandug;

import android.content.Context;
import android.content.res.Resources;
import android.widget.GridLayout.LayoutParams;
import android.view.View;
import android.widget.GridLayout;

/**
 * Created by Олег Демьянченко on 20.02.2018.
 */

public class GameButton extends android.support.v7.widget.AppCompatButton{
    String id;

    public GameButton(Context context) {
        super(context);
    }

    public GameButton (Context context, GridLayout layout, int i, Resources resources) {
        super(context);
        id = (resources.getStringArray(R.array.ButtonsIds))[i];
        this.setText(id);
        int ButtonLayoutId = View.generateViewId();
        this.setId(ButtonLayoutId);
        LayoutParams layoutParams = new LayoutParams(GridLayout.spec(i % 3), GridLayout.spec((i - (i%3))/3));
        int x = (i % 3)*200 + 300;
        int y = (i - (i % 3))*100;
        setLayoutParams(layoutParams);
        layout.addView(this);
    }
}
