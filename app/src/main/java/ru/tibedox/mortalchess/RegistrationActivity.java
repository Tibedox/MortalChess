package ru.tibedox.mortalchess;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class RegistrationActivity extends AppCompatActivity {
    MyButton buttonReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        buttonReg = new MyButton(this, V.screenW/2-buttonReg.sizeWidth/2, V.screenH/10*4, buttonReg.sizeWidth, buttonReg.sizeHeight, "Зарегистрироваться");
    }

    class MyButton{
        Button button;
        int sizeWidth = V.screenW/2;
        int sizeHeight = V.screenH/10;
        MyButton(final RegistrationActivity activity, int x, int y, int sizeWidth, int sizeHeight, final String text){
            button = new Button(activity);
            activity.addContentView(button, new RelativeLayout.LayoutParams(sizeWidth, sizeHeight));
            button.setText(text);
            button.setX(x);
            button.setY(y);
           /* button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.click(text);
                }
            });*/
        }
    }
}
