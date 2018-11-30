package ru.tibedox.mortalchess;

class Space extends Element{
    int color;
    Figure occupied;
    Space(MainActivity main, int x, int y, int color){
        super(main, x, y);
        if(color==V.WHITE) setPic(R.drawable.space_white);
        else setPic(R.drawable.space_black);
        this.color = color;
        occupied=null;
        //occupy(null);
    }
    public void occupy(Figure figure){
        occupied = figure;
    }
}
