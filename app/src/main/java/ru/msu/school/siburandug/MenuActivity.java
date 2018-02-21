package ru.msu.school.siburandug;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MenuActivity extends AppCompatActivity implements View.OnClickListener{
    final static int[] prices= {20, 30, 40, 50, 60, 70, 80, 90}; //цена энного завода - СУММА всех заводов до него!
    SharedPreferences sPref;
    final static String TOTAL_MONEY = "total_money";
    final static String MY_PREFERENCES = "my preferences";
    public int money;
    Button btnMoney;
    Button btnNullMoney;
    /*Button buttonGame1;
    Button buttonGame2;
    Button buttonGame3;
    Button buttonGame4;
    Button buttonGame5;
    Button buttonGame6;
    Button buttonGame7;
    Button buttonGame8;
    Button buttonGame9;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_menu);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initializeCurrentMoney();


        btnMoney = (Button)findViewById(R.id.btnMoney);
        btnMoney.setOnClickListener(this);


        btnNullMoney = (Button)findViewById(R.id.btnStartFirstGame);
        btnNullMoney.setOnClickListener(this);

        Button[] gameButtons = new Button[9];
        Resources res = getResources();
        LinearLayout layout = (LinearLayout)findViewById(R.id.mainLayout);
        for (int i = 0; i < gameButtons.length; i += 1) {
            (gameButtons[i]) = new Button(this);
            /**/
            //Toast.makeText(this,(gameButtons[i]).getX() + "", Toast.LENGTH_SHORT).show();
            (gameButtons[i]).setText("kek " + i);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            //(gameButtons[i]).setLayoutParams(layoutParams);
            layout.addView(gameButtons[i], layoutParams);
            //int x = 100 + (i % 3)*200;
            //int y = 100 + (i - (i % 3))*100;
            /*(gameButtons[i]).setX(x);
            (gameButtons[i]).setY(y);*/
            //(gameButtons[i]).layout(x, y, 800 - x, 600 - y);

        }

        /*buttonGame1 = (Button)findViewById(R.id.buttonGame1);
        buttonGame1.setOnClickListener(this);
        buttonGame2 = (Button)findViewById(R.id.buttonGame2);
        buttonGame2.setOnClickListener(this);
        buttonGame3 = (Button)findViewById(R.id.buttonGame3);
        buttonGame3.setOnClickListener(this);
        buttonGame4 = (Button)findViewById(R.id.buttonGame4);
        buttonGame4.setOnClickListener(this);
        buttonGame5 = (Button)findViewById(R.id.buttonGame5);
        buttonGame5.setOnClickListener(this);
        buttonGame6 = (Button)findViewById(R.id.buttonGame6);
        buttonGame6.setOnClickListener(this);
        buttonGame7 = (Button)findViewById(R.id.buttonGame7);
        buttonGame7.setOnClickListener(this);
        buttonGame8 = (Button)findViewById(R.id.buttonGame8);
        buttonGame8.setOnClickListener(this);
        buttonGame9 = (Button)findViewById(R.id.buttonGame9);
        buttonGame9.setOnClickListener(this);*/
        //loadVar();
    }

    private void initializeCurrentMoney() {
        sPref = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
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
            case R.id.btnMoney:

                plusMoney();
                break;
            case R.id.btnStartFirstGame:
                nullMoney();
                break;

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
