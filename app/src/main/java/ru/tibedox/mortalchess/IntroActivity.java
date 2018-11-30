package ru.tibedox.mortalchess;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import static java.lang.System.exit;

public class IntroActivity extends AppCompatActivity {
    MyButton buttonNewGame;
    MyButton buttonContinue;
    MyButton buttonRegistration;
    MyButton buttonEnter;
    MyButton buttonRules;
    MyButton buttonExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setBasicConstants();
        createButtons();
    }

    void createButtons(){
        int sizeButtonsWidth = V.screenW/2;
        int sizeButtonsHeight = V.screenH/10;
        buttonNewGame = new MyButton(this, V.screenW/2-sizeButtonsWidth/2, V.screenH/10, sizeButtonsWidth, sizeButtonsHeight, "Новая игра");
        buttonContinue = new MyButton(this, V.screenW/2-sizeButtonsWidth/2, V.screenH/10*2, sizeButtonsWidth, sizeButtonsHeight, "Продолжить");
        buttonRegistration = new MyButton(this, V.screenW/2-sizeButtonsWidth/2, V.screenH/10*3, sizeButtonsWidth, sizeButtonsHeight, "Регистрация");
        buttonEnter = new MyButton(this, V.screenW/2-sizeButtonsWidth/2, V.screenH/10*4, sizeButtonsWidth, sizeButtonsHeight, "Вход");
        buttonRules = new MyButton(this, V.screenW/2-sizeButtonsWidth/2, V.screenH/10*5, sizeButtonsWidth, sizeButtonsHeight, "Правила");
        buttonExit = new MyButton(this, V.screenW/2-sizeButtonsWidth/2, V.screenH/10*6, sizeButtonsWidth, sizeButtonsHeight, "Выход");
    }
    private void setBasicConstants(){
        // задаём разрешение экрана
        V.screenW = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        V.screenH = getApplicationContext().getResources().getDisplayMetrics().heightPixels;

        V.SIZE = V.screenW/8; // размер клетки
        V.TOP_PANEL = getStatusBarHeight(); // высота панели задач
    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void click(String text){
        switch (text){
            case "Новая игра":
                startActivity (new Intent(this, MainActivity.class)); //  переключаемся с IntroActivity на MainActivity
                finish();
                break;
            case "Выход": exit(0);
            default:break;
        }
    }
}

class MyButton{
    Button button;

    MyButton(final IntroActivity activity, int x, int y, int sizeWidth, int sizeHeight, final String text){
        button = new Button(activity);
        activity.addContentView(button, new RelativeLayout.LayoutParams(sizeWidth, sizeHeight));
        button.setText(text);
        button.setX(x);
        button.setY(y);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.click(text);
            }
        });
    }
}