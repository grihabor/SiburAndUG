package ru.msu.school.siburandug;

import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.support.constraint.ConstraintSet;
import android.view.View;

/**
 * Created by Олег Демьянченко on 20.02.2018.
 */

public class GameButton extends android.support.v7.widget.AppCompatButton{
    String id;

    public GameButton(Context context) {
        super(context);
    }

    public GameButton (Context context, ConstraintLayout layout, int i, Resources resources) {
        super(context);
        id = (resources.getStringArray(R.array.ButtonsIds))[i];
        this.setText(id);
        int ButtonLayoutId = View.generateViewId();
        this.setId(ButtonLayoutId);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        int x = (i % 3)*200 + 300;
        int y = (i - (i % 3))*100;
        setLayoutParams(layoutParams);
        layout.addView(this);

        ConstraintSet set = new ConstraintSet();
        set.clone(layout);
        set.connect(this.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, x);

        set.applyTo(layout);
    }
}
