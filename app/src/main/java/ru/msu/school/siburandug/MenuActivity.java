package ru.msu.school.siburandug;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;


public class MenuActivity extends AppCompatActivity implements View.OnClickListener{
    final static int[] prices= {20, 30, 40, 50, 60, 70, 80, 90}; //цена энного завода - СУММА всех заводов до него!
    SharedPreferences sPref;
    public final static String TOTAL_MONEY = "total_money";
    public final static String MY_PREFERENCES = "my preferences";
    public int money;
    //Button btnMoney;
    //Button btnNullMoney;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        sPref = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);

        getSupportActionBar().hide();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initializeCurrentMoney();

        GameButton[] gameButtons = new GameButton[9];

        ButtonData[] buttonData = new ButtonData[9];

        buttonData[0] = new ButtonData("FirstGame", 0, "Pyrolysis", "1");
        buttonData[1] = new ButtonData("FirstGame", 20, "Game2", "2");
        buttonData[2] = new ButtonData("FirstGame", 30, "Game3", "3");
        buttonData[3] = new ButtonData("FirstGame", 40, "Game4", "4");
        buttonData[4] = new ButtonData("FirstGame", 50, "Game5", "5");
        buttonData[5] = new ButtonData("FirstGame", 60, "Game6", "6");
        buttonData[6] = new ButtonData("FirstGame", 70, "Game7", "7");
        buttonData[7] = new ButtonData("FirstGame", 80, "Game8", "8");
        buttonData[8] = new ButtonData("FirstGame", 90, "Game9", "9");

        Resources res = getResources();
        GridLayout layout = (GridLayout)findViewById(R.id.grid);
        layout.setId(View.generateViewId());
        layout.setColumnCount(3);
        layout.setRowCount(3);

        /*btnMoney = findViewById(R.id.btnMoney);
        btnMoney.setOnClickListener(this);
        layout.addView(btnMoney);*/

        /*btnNullMoney = findViewById(R.id.btnMoney);
        btnNullMoney.setOnClickListener(this);
        layout.addView(btnNullMoney);*/

        for (int i = 0; i < gameButtons.length; i += 1) {
            (gameButtons[i]) = new GameButton(this, layout, sPref, i, buttonData[i]);
        }

    }

    private void initializeCurrentMoney() {
        if(checkIfAvaluable(2)==1){
            if(checkIfAvaluable(3)==1){
                if(checkIfAvaluable(4)==1){
                    if(checkIfAvaluable(5)==1){
                        if(checkIfAvaluable(6)==1){
                            if(checkIfAvaluable(7)==1){
                                if(checkIfAvaluable(8)==1){
                                    if(checkIfAvaluable(9)==1){
                                        //деньги = тотал минус цена всех
                                        money = sPref.getInt(TOTAL_MONEY,0)-prices[7];
                                    } else money = sPref.getInt(TOTAL_MONEY,0)-prices[6];
                                } else {
                                    money = sPref.getInt(TOTAL_MONEY,0)-prices[5];
                                }
                            } else {
                                money = sPref.getInt(TOTAL_MONEY,0)-prices[4];
                            }
                        }else {
                            money = sPref.getInt(TOTAL_MONEY,0)-prices[3];
                        }
                    } else{
                        money = sPref.getInt(TOTAL_MONEY,0)-prices[2];
                    }
                } else {
                    money = sPref.getInt(TOTAL_MONEY,0)-prices[1];
                }
            } else {
                money = sPref.getInt(TOTAL_MONEY,0)-prices[0];
            }
        } else{
            money = sPref.getInt(TOTAL_MONEY,0);
        }

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /*case R.id.btnMoney:

                plusMoney();
                break;
            case R.id.btnNullMoney:
                nullMoney();
                break;*/

            /*case R.id.buttonGame1:
                startFirstGame();
                break;

            case R.id.buttonGame2:
                if(checkIfAvaluable(2) == 1){
                    startFirstGame();
                } else Toast.makeText(this,"У вас недостаточно средств!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonGame3:
                if(checkIfAvaluable(3) == 1){
                    startFirstGame();
                } else Toast.makeText(this,"У вас недостаточно средств!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonGame4:
                if(checkIfAvaluable(4) == 1){
                    startFirstGame();
                } else Toast.makeText(this,"У вас недостаточно средств!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonGame5:
                if(checkIfAvaluable(5) == 1){
                    startFirstGame();
                } else Toast.makeText(this,"У вас недостаточно средств!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonGame6:
                if(checkIfAvaluable(6) == 1){
                    startFirstGame();
                } else Toast.makeText(this,"У вас недостаточно средств!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonGame7:
                if(checkIfAvaluable(7) == 1){
                    startFirstGame();
                } else Toast.makeText(this,"У вас недостаточно средств!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonGame8:
                if(checkIfAvaluable(8) == 1){
                    startFirstGame();
                } else Toast.makeText(this,"У вас недостаточно средств!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonGame9:
                if(checkIfAvaluable(9) == 1){
                    startFirstGame();
                } else Toast.makeText(this,"У вас недостаточно средств!", Toast.LENGTH_SHORT).show();
                break;*/

            default:
                Toast.makeText(this,"+", Toast.LENGTH_SHORT).show();
                break;
        }



    }

    private void nullMoney() {
        sPref = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(TOTAL_MONEY, 0);
        editor.commit();
    }

    private void startFirstGame() {
        Intent intent = new Intent(MenuActivity.this, FirstGame.class);
        startActivity(intent);
        //finish();
    }



    private void plusMoney(){
        sPref = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        //editor.putInt(MONEY, sPref.getInt(MONEY,0)+1);
        editor.putInt(TOTAL_MONEY, sPref.getInt(TOTAL_MONEY,0)+1);
        editor.commit();
        money++;
        Toast.makeText(MenuActivity.this,"Деньги в файле ="+sPref.getInt(TOTAL_MONEY, 0)+"! Отображаемые деньги = "+money+"!",Toast.LENGTH_SHORT).show();
    }

    private int checkIfAvaluable(int n){
        sPref = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        if(prices[n-2]<=sPref.getInt(TOTAL_MONEY,0)) {
            return 1;
        } else return 0;
    }


   /* private void savePreferences() {
        sPref = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(MONEY, Var.money);
        editor.commit();
        Toast.makeText(MenuActivity.this,"Деньги в варе = "+Var.money+"; в файле ="+sPref.getInt(MONEY, 0)+"!",Toast.LENGTH_SHORT).show();
    }*/



}
