package ru.tibedox.mortalchess;

class Board{
    Space space[][];
    Board(MainActivity main){
        space = new Space[8][8];
        for(int j=0; j<8; j++)
            for(int i=0; i<8; i++) {
                if(j%2==0)
                    if(i%2 == 0) space[i][j] = new Space(main, i, j, V.WHITE);
                    else space[i][j] = new Space(main, i, j, V.BLACK);
                else
                    if(i%2 == 0) space[i][j] = new Space(main, i, j, V.BLACK);
                    else space[i][j] = new Space(main, i, j, V.WHITE);
            }
    }
}
