package ru.msu.school.siburandug;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;


public class PlayActivity extends AppCompatActivity {
    /**
     * Объявления глобальных переменных:
     */
    private ImageView thermometer, mark, plus, minus, end, exit;//картинки
    private ConstraintLayout markL, mainL;//разметки
    private int x, y, screenWidth, screenHeight, money;//координаты стрелки и размеры экрана, деньги - целые
    private MyTimer myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        /**
         *Ловим лейауты mainL и markL
         */
        mainL = findViewById(R.id.mainL);
        markL = findViewById(R.id.markL);

        /**
         *Ловим картинки, нрисованные в хемеэле
         */
        thermometer = findViewById(R.id.thermometer);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        mark = findViewById(R.id.mark);
        exit = findViewById(R.id.exit);

        /**
         *Инициализация картинок изображением
         */
        thermometer.setImageResource(R.drawable.thermometer);
        mark.setImageResource(R.drawable.mark);
        plus.setImageResource(R.drawable.plus);
        minus.setImageResource(R.drawable.minus);
        end = new ImageView(this);


        /**
         *Навешивание слушателей клика
         */
        plus.setOnClickListener(myOnClickListener);
        minus.setOnClickListener(myOnClickListener);

        /**
         *Узнали размеры экрана по двум осям
         */

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenWidth = displaymetrics.widthPixels;
        screenHeight = displaymetrics.heightPixels;

        /**
         *задали начальные координаты стрелки - по центру экрана
         */
        x = 100;
        y = screenHeight / 2;

        /**
         *Создаем таймер своего класса и запускаем его
         */
        myTimer = new MyTimer();
        myTimer.start();
    }

    /**
     * Пишем свой слушатель клика, один на все картинки(вообще-то можно и каждый на каждую, благо их
     * две, но красивое программирование, все дела
     */
    @NonNull
    protected View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(@NonNull View v) {
            switch (v.getId()) {
                case R.id.plus:
                    y -= 50;
                    set();
                    break;

                case R.id.minus:
                    y += 50;
                    set();
                    break;
                default:
                    System.exit(0);
                    break;
            }


        }
    };

    /**
     * Установка новых координат стрелочки согласно текущим значениям x и y
     */
    protected void set() {
        markL.setY(y);
        markL.setX(x);
    }

    /**
     * Функция вызываемая при завершении игры
     */

    protected void finishing() {
        end.setImageResource((y > (screenHeight) || y < 0) ? R.drawable.lose : R.drawable.win);
        mainL.removeAllViews();
        mainL.addView(end);
        myTimer.cancel();
        end.setOnClickListener(myOnClickListener);
        }



    /**
     * Описываем класс своего таймера, переопределяя некоторые методы
     */
    class MyTimer extends CountDownTimer {//мой таймер, унаследованный от стандартного

        MyTimer() {
            super(30000, 1000);//создание объекта и указание времени работы(30 сек) и интервала отсчета(1 сек)
        }

        @Override
        public void onTick(long millisUntilFinished) {//каждые 1 сек.
            if (y > (screenHeight) || y < 0) {//если стрелка "убежала"
                finishing();//вызываю функцию завершения игры
            }
            y += Math.random() * 200 * Math.pow(-1, (int) (Math.random() * 2));//иначе рандомно меняю высоту положения стрелочки
            set();//и устанавливаю ее
        }

        @Override
        public void onFinish() {//в конце работы таймера(через 30 сек.)
            money += 20;//дали монет
            finishing();//вызвали функцию завершения игры
        }


    }
}
