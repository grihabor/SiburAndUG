package ru.msu.school.siburandug;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.widget.GridLayout.LayoutParams;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Олег Демьянченко on 20.02.2018.
 */

public class GameButton extends android.support.v7.widget.AppCompatButton implements View.OnClickListener {
    int Price;
    String className;
    String textId;
    SharedPreferences sPref;
    Context context;
    boolean Available;

    public GameButton(Context context) {
        super(context);
    }

    public GameButton (Context context, GridLayout layout, SharedPreferences sPref, int i, ButtonData data) {
        super(context);

        Price = data.Price;
        setText(data.text);
        this.sPref = sPref;
        this.context = context;
        this.textId = data.textId;
        Available = sPref.getBoolean(textId, false);

        int ButtonLayoutId = View.generateViewId();
        this.setId(ButtonLayoutId);

        this.setBackgroundResource(Available ? R.color.colorAvailable : R.color.colorUnavailable);

        LayoutParams layoutParams = new LayoutParams(GridLayout.spec((i - (i%3))/3), GridLayout.spec(i % 3));
        setLayoutParams(layoutParams);

        layout.addView(this);

        setOnClickListener(this);
    }

    @Override
    public void onClick (View view) {
        int money = sPref.getInt(MenuActivity.TOTAL_MONEY, 0);
        SharedPreferences.Editor editor = sPref.edit();
        if (!Available) {
            if (money >= Price) {
                Available = true;
                this.setBackgroundResource(R.color.colorAvailable);
                editor.putInt(MenuActivity.TOTAL_MONEY, money - Price);
                editor.putBoolean(textId, true);
                Toast.makeText(context, "Спасибо за покупку!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "У вас недостаточно средств!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            editor.putInt(MenuActivity.TOTAL_MONEY, money + 5);
            Toast.makeText(context, "У вас " + money + " сибуриков", Toast.LENGTH_SHORT).show();
        }

        editor.apply();
    }
}
