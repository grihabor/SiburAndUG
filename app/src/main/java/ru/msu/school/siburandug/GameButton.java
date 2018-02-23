package ru.msu.school.siburandug;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.GridLayout.LayoutParams;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

/*
  Created by Oleg Demianchenko on 20.02.2018.
 */

public class GameButton extends android.support.v7.widget.AppCompatButton implements View.OnClickListener {
    int Price;
    String className;
    String textId;
    SharedPreferences sPref;
    Context context;
    boolean Available;

    public GameButton (Context context) {
        super(context);

        this.context = context;

        setOnClickListener(this);
    }

    void SetPosition (int i) {
        LayoutParams layoutParams = new LayoutParams(GridLayout.spec((i - (i%3))/3), GridLayout.spec(i % 3));
        setLayoutParams(layoutParams);
    }

    void SetButtonData (ButtonData data) {
        Price = data.Price;
        setText(data.text);
        this.textId = data.textId;
        this.className = data.className;
    }

    void SetSharedPreference (SharedPreferences sPref) {
        this.sPref = sPref;
    }

    void CheckAccess () {
        Available = sPref.getBoolean(textId, false);
        this.setBackgroundResource(Available ? R.color.colorAvailable : R.color.colorUnavailable);
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
                Toast.makeText(context, "У вас недостаточно средств! Цена " + Price, Toast.LENGTH_SHORT).show();
            }
        }
        else {

            try {
                Intent intent = new Intent (context, Class.forName(className));
                context.startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        editor.apply();
    }
}