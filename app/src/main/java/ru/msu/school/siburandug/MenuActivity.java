package ru.msu.school.siburandug;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;


public class MenuActivity extends AppCompatActivity {
    SharedPreferences sPref;
    public final static String TOTAL_MONEY = "total_money";
    public final static String MY_PREFERENCES = "my preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        sPref = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);

        getSupportActionBar().hide();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        GameButton[] gameButtons = new GameButton[9];

        ButtonData[] buttonData = new ButtonData[9];

        buttonData[0] = new ButtonData(FirstGame.class.getCanonicalName(), 0, "Pyrolysis", "1");
        buttonData[1] = new ButtonData(FirstGame.class.getCanonicalName(), 20, "Game2", "2");
        buttonData[2] = new ButtonData(FirstGame.class.getCanonicalName(), 30, "Game3", "3");
        buttonData[3] = new ButtonData(FirstGame.class.getCanonicalName(), 40, "Game4", "4");
        buttonData[4] = new ButtonData(FirstGame.class.getCanonicalName(), 50, "Game5", "5");
        buttonData[5] = new ButtonData(FirstGame.class.getCanonicalName(), 60, "Game6", "6");
        buttonData[6] = new ButtonData(FirstGame.class.getCanonicalName(), 70, "Game7", "7");
        buttonData[7] = new ButtonData(FirstGame.class.getCanonicalName(), 80, "Game8", "8");
        buttonData[8] = new ButtonData(FirstGame.class.getCanonicalName(), 90, "Game9", "9");

        Resources res = getResources();
        GridLayout layout = (GridLayout)findViewById(R.id.grid);
        layout.setId(View.generateViewId());
        layout.setColumnCount(3);
        layout.setRowCount(3);

        for (int i = 0; i < gameButtons.length; i += 1) {
            (gameButtons[i]) = new GameButton(this);
            (gameButtons[i]).SetButtonData(buttonData[i]);
            (gameButtons[i]).SetPosition(i);
            (gameButtons[i]).SetSharedPreference(sPref);
            (gameButtons[i]).CheckAccess();
            layout.addView(gameButtons[i]);
        }

    }

}