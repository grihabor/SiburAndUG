package ru.msu.school.siburandug;
//ИМПРОТЫ:
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class FirstGame extends AppCompatActivity {
    //ГЛОБАЛЬНЫЕ ПЕРЕМЕННЫЕ:
    int ScreenWidth, ScreenHeigth;//ширина и высота экрана
    int x=ScreenHeigth/2,y=ScreenWidth/2, money;// текущие координаты стрелки и количество денег
    ImageView Mark;//объект - картинка
    ConstraintLayout ML, newL;//разметки - гланая и для стрелки
    MyTimer timer;//мой таймер

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.first_game_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();//получили объект - параметры экрана
        ScreenWidth = display.getWidth();//вырвали из него ширину
        ScreenHeigth = display.getHeight();//и высоту
        ImageView Thermometer = findViewById(R.id.thermometer);//нашли вьюшку термометра
        Thermometer.setImageResource(R.drawable.thermometer);//присвоили вьюхе изображение
        Mark = findViewById(R.id.mark);//нашли вьюшку стрелки
        ML = findViewById(R.id.MainL);//поймали главную разметку
        newL = findViewById(R.id.markL);//поймали разметку стрелки
//отрисовываем вызовом set();
        set();
        timer = new MyTimer();//создаем запускаем таймер
        timer.start();
    }

    public void set(){//отвечает за отрисовку стрелочки
        newL.setX(x);//новый x
        newL.setY(y);//новый Y
    }

    public void plus(View view){
        y-=100;//увеличили координату по высоте
        set();//нарисовали с новой координатой
    }

    public void minus(View view){
        y+=100;//уменьшили координату по высоте
        set();//нарисовали с новой координатой
    }

    public void finita() {//завершение игры
        timer.cancel();//остановили таймер
        ImageView end = new ImageView(this);//создали вьюху конца
        ML.removeAllViews();//убрали лишнее
        ML.addView(end, 0);//добавили нашу вьюху
        end.setImageResource((100 > y) || (y > 700) ? R.drawable.lose : (int) R.drawable.win);//установили поздравление или надпись "ты проиграл" во вьюху
    }

    class MyTimer extends CountDownTimer {//мой таймер, унаследованный от стандартного
        MyTimer() {
            super(30000, 1000);//создание объекта и указание времени работы(30 сек) и интервала отсчета(1 сек)
        }

        @Override
        public void onTick(long millisUntilFinished) {//каждые 1 сек.
            if(y>700||y<100){//если стрелка "убежала"
                finita();//вызываю функцию завершения игры
            }
            y+=Math.random()*200*Math.pow(-1,(int)(Math.random()*2));//иначе рандомно меняю высоту положения стрелочки
            set();//и устанавливаю ее
        }

        @Override
        public void onFinish() {//в конце работы таймера(через 30 сек.)
            money+=20;//дали монет
            finita();//вызвали функцию завершения игры
        }
    }

}


       /* import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.Toast;




public class FirstGame extends AppCompatActivity implements View.OnClickListener{


    Button btnFirstGameMoney;
    Button btnToMenu;
    SharedPreferences sPref;

    final static String TOTAL_MONEY = "total_money";
    final static String MY_PREFERENCES = "my preferences";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.first_game_activity);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Toast.makeText(FirstGame.this,"Деньги в варе = "+Var.money+"; в файле ="+sPref.getInt(MONEY, 0)+"!",Toast.LENGTH_SHORT).show();

        btnFirstGameMoney = (Button)findViewById(R.id.btnFirstGameMoney);
        btnFirstGameMoney.setOnClickListener(this);

        btnToMenu = (Button) findViewById(R.id.btnToMenu);
        btnToMenu.setOnClickListener(this);


    }
    private void startMenu() {
        //savePreferences();
        Intent intent = new Intent(FirstGame.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnFirstGameMoney:
                plusMoney();
                break;

            case R.id.btnToMenu:
                startMenu();
                break;

            default:
                Toast.makeText(this,"+", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void plusMoney(){
            sPref = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
            SharedPreferences.Editor editor = sPref.edit();
            editor.putInt(TOTAL_MONEY, sPref.getInt(TOTAL_MONEY,0)+1);
            editor.commit();
        Toast.makeText(FirstGame.this,"Деньги в файле ="+sPref.getInt(TOTAL_MONEY, 0)+"!",Toast.LENGTH_SHORT).show();
    }

    private void savePreferences() {
        sPref = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(TOTAL_MONEY, Var.money);
        editor.commit();
        Toast.makeText(FirstGame.this,"Деньги в варе = "+Var.money+"; в файле ="+sPref.getInt(TOTAL_MONEY, 0)+"!",Toast.LENGTH_SHORT).show();
    }

}