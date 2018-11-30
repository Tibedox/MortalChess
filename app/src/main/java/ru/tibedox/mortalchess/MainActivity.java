package ru.tibedox.mortalchess;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Board board;
    Figure figure[][] = new Figure[16][2];
    Arrangement arrangement;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setBasicConstants();
        textView = (TextView)findViewById(R.id.text);
        textView.setText(""+getStatusBarHeight());
        board = new Board(this);
        arrangement = new Arrangement(this, V.WHITE);
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
}

