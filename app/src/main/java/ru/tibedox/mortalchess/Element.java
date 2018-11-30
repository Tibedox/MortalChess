package ru.tibedox.mortalchess;

import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Element {
    ImageView image;
    int x, y;
    Element(MainActivity main, int x, int y){
        image = new ImageView(main);
        this.x = x;
        this.y = y;
        outXY(x, y);
        main.addContentView(image, new RelativeLayout.LayoutParams(V.SIZE, V.SIZE));
    }
    public void outXY(int x, int y){
        image.setX(x*V.SIZE);
        image.setY(y*V.SIZE);
    }
    public void moveXY(float x, float y){
        image.setX(x-V.TOP_PANEL);
        image.setY(y-V.TOP_PANEL);
    }
    public void setPic(int pic){
        image.setImageResource(pic);
    }
}


