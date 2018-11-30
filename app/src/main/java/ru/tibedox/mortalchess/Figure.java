package ru.tibedox.mortalchess;

import android.view.MotionEvent;
import android.view.View;

public class Figure extends Element {
    int type; // тип фигуры
    int color; // цвет
    int newX, newY; // куда перемещаем фигуру
    float realX, realY; // реальные экранные координаты фигуры в момент перемещения
    MainActivity main;

    Figure(MainActivity main, int x, int y, int color, int type) {
        super(main, x, y);
        this.main = main;
        this.color = color;
        this.type = type;
        setType();
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                touched(event);
                return true;
            }
        });
    }

    private void setType() {
        int img;
        if (color == V.WHITE)
            switch (type) {
                case V.PAWN:
                    img = R.drawable.pawn_white;
                    break;
                case V.BISHOP:
                    img = R.drawable.bishop_white;
                    break;
                case V.KNIGHT:
                    img = R.drawable.knight_white;
                    break;
                case V.ROOK:
                    img = R.drawable.rook_white;
                    break;
                case V.QUEEN:
                    img = R.drawable.queen_white;
                    break;
                default:
                    img = R.drawable.king_white;
                    break;
            }
        else
            switch (type) {
                case V.PAWN:
                    img = R.drawable.pawn_black;
                    break;
                case V.BISHOP:
                    img = R.drawable.bishop_black;
                    break;
                case V.KNIGHT:
                    img = R.drawable.knight_black;
                    break;
                case V.ROOK:
                    img = R.drawable.rook_black;
                    break;
                case V.QUEEN:
                    img = R.drawable.queen_black;
                    break;
                default:
                    img = R.drawable.king_black;
                    break;
            }
        setPic(img);
    }

    private void touched(MotionEvent event) {
        int action = event.getAction(); // получаем нажатие
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                image.bringToFront();
                break; // нажали
            case MotionEvent.ACTION_MOVE:
                realX = event.getRawX() - V.SIZE / 2;
                realY = event.getRawY() - V.SIZE / 2;
                moveXY(realX, realY);
                break; // перемещаем палец по экрану
            case MotionEvent.ACTION_UP:
                newX = (int) (realX / V.SIZE);
                newY = (int) (realY / V.SIZE);
                if(newX<0 || newX>7 || newY<0 || newY>7){ // если вышли за пределы доски, то взад
                    outXY(x, y);
                }
                if (main.board.space[newX][newY].occupied==null){ // если клетка свободна, идём тудой
                    main.board.space[x][y].occupied=null;
                    x=newX;
                    y=newY;
                    main.board.space[x][y].occupy(this);
                    outXY(x, y);
                } else { // если клетка занята
                    outXY(x, y);
                    main.textView.setText(""+newX+" "+newY+" "+main.board.space[newX][newY].occupied);
                }
                break; // подняли палец
            case MotionEvent.ACTION_CANCEL:
                ;
                break; // некорректное завершение, без ACTION_UP
        }
    }
}

// создание расстановка фигур перед началом игры
class Arrangement {
    Arrangement(MainActivity main, int color) {
        int c;
        if (color == V.WHITE) c = V.BLACK;
        else c = V.WHITE;
        main.figure[0][0] = new Figure(main, 0, 0, c, V.ROOK);
        main.board.space[0][0].occupy(main.figure[0][0]);
        main.figure[1][0] = new Figure(main, 1, 0, c, V.KNIGHT);
        main.board.space[1][0].occupy(main.figure[1][0]);
        main.figure[2][0] = new Figure(main, 2, 0, c, V.BISHOP);
        main.board.space[2][0].occupy(main.figure[2][0]);
        if (color == V.WHITE) {
            main.figure[3][0] = new Figure(main, 3, 0, c, V.QUEEN);
            main.board.space[3][0].occupy(main.figure[3][0]);
            main.figure[4][0] = new Figure(main, 4, 0, c, V.KING);
            main.board.space[4][0].occupy(main.figure[4][0]);
        } else {
            main.figure[3][0] = new Figure(main, 3, 0, c, V.KING);
            main.board.space[3][0].occupy(main.figure[3][0]);
            main.figure[4][0] = new Figure(main, 4, 0, c, V.QUEEN);
            main.board.space[4][0].occupy(main.figure[4][0]);
        }
        main.figure[5][0] = new Figure(main, 5, 0, c, V.BISHOP);
        main.board.space[5][0].occupy(main.figure[5][0]);
        main.figure[6][0] = new Figure(main, 6, 0, c, V.KNIGHT);
        main.board.space[6][0].occupy(main.figure[6][0]);
        main.figure[7][0] = new Figure(main, 7, 0, c, V.ROOK);
        main.board.space[7][0].occupy(main.figure[7][0]);
        for (int i = 0; i < 8; i++) {
            main.figure[i+8][0] = new Figure(main, i, 1, c, V.PAWN);
            main.board.space[i][1].occupy(main.figure[i+8][0]);
        }
        if (color == V.WHITE) c = V.WHITE;
        else c = V.BLACK;
        main.figure[0][1] = new Figure(main, 0, 7, c, V.ROOK);
        main.board.space[0][7].occupy(main.figure[0][1]);
        main.figure[1][1] = new Figure(main, 1, 7, c, V.KNIGHT);
        main.board.space[1][7].occupy(main.figure[1][1]);
        main.figure[2][1] = new Figure(main, 2, 7, c, V.BISHOP);
        main.board.space[2][7].occupy(main.figure[2][1]);
        if (color == V.WHITE) {
            main.figure[3][1] = new Figure(main, 3, 7, c, V.QUEEN);
            main.board.space[3][7].occupy(main.figure[3][1]);
            main.figure[4][1] = new Figure(main, 4, 7, c, V.KING);
            main.board.space[4][7].occupy(main.figure[4][1]);
        } else {
            main.figure[3][1] = new Figure(main, 3, 7, c, V.KING);
            main.board.space[3][7].occupy(main.figure[3][1]);
            main.figure[4][1] = new Figure(main, 4, 7, c, V.QUEEN);
            main.board.space[4][7].occupy(main.figure[4][1]);
        }
        main.figure[5][1] = new Figure(main, 5, 7, c, V.BISHOP);
        main.board.space[5][7].occupy(main.figure[5][1]);
        main.figure[6][1] = new Figure(main, 6, 7, c, V.KNIGHT);
        main.board.space[6][7].occupy(main.figure[6][1]);
        main.figure[7][1] = new Figure(main, 7, 7, c, V.ROOK);
        main.board.space[7][7].occupy(main.figure[7][1]);
        for (int i = 0; i < 8; i++) {
            main.figure[i+8][1] = new Figure(main, i, 6, c, V.PAWN);
            main.board.space[i][6].occupy(main.figure[i+8][1]);
        }
    }
}