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

    public GameButton (Context context, int i, Resources resources) {
        super(context);
        id = (resources.getStringArray(R.array.ButtonsIds))[i];
        /*int num = resources.getIdentifier("Pyrolysis", "string", "ru.msu.school.siburandug");
        System.out.println(resources.getString(num));*/
        setX (100 + (i % 3)*200);
        setY (100 + (i - (i % 3))*100);

        setText(id);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));

    }
}
