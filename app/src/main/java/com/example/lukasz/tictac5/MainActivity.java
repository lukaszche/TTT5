package com.example.lukasz.tictac5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.StringTokenizer;

public class MainActivity extends Activity {

    final static int maxN=12;
    private Context context;
    private ImageView[][] ivCell = new ImageView[maxN][maxN];

    private Drawable[] drawCell= new Drawable[4];

    private Button bPlay;
    private TextView tvturn;

    private int[][] cellSeed = new int [maxN][maxN]; //0 null,1 red, 2 green
    private int winner;
    private boolean firstMove;
    private int xCurr,yCurr;
    private int turnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        setListen();
        loadResources();
        designBoardGame();
    }

    private void setListen() {
        bPlay =(Button) findViewById(R.id.btnNewGame);
        tvturn = (TextView) findViewById(R.id.txTurn);

        bPlay.setText("Play Game");
        tvturn.setText("Press button Play Game");

        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGame();
                playGame();
            }
        });

    }

    private void playGame() {
        Random r=new Random();
        turnPlay=r.nextInt(2)+1;

        if(turnPlay==1){
            Toast.makeText(context,"Red play first!",Toast.LENGTH_SHORT).show();
            playerRedTurn();
        }else{
            Toast.makeText(context,"Green play first!",Toast.LENGTH_SHORT).show();
            playerGreenTurn();
        }
    }

    private void playerGreenTurn() {
        tvturn.setText("Red");
        tvturn.setTextColor(Color.RED);
        //if(firstMove){
        //    makeAMove();
        //}
        isClicked=false;
    }

    private void makeAMove() {
        ivCell[xCurr][yCurr].setImageDrawable(drawCell[turnPlay]);
        cellSeed[xCurr][yCurr]=turnPlay;
        
        if(noEmpty()){
            Toast.makeText(context, "It's a DRAW!!!",Toast.LENGTH_SHORT).show();
            return;
        }else if (checkWin()){
            if(winner==1){
                Toast.makeText(context,"Winner is GREEN",Toast.LENGTH_SHORT).show();
                tvturn.setText("Winner is Green");
            }else{
                Toast.makeText(context,"Winner is RED",Toast.LENGTH_SHORT).show();
                tvturn.setText("Winner is Red");
            }
            return;
        }
        
        if(turnPlay==1){
            turnPlay=2;
            playerGreenTurn();
        }else if(turnPlay==2){
            turnPlay=1;
            playerRedTurn();
        }
    }

    private boolean checkWin() {
        if(winner!=0){
            return true;
        }
        VectorCheck(xCurr,0,0,1,xCurr,yCurr);
        VectorCheck(0,yCurr,1,0,xCurr,yCurr);
        if(xCurr+yCurr>maxN-1){
            VectorCheck(maxN-1,xCurr+yCurr-maxN+1,-1,1,xCurr,yCurr);
        }else{
            VectorCheck(xCurr+yCurr,0,-1,1,xCurr,yCurr);
        }

        if(xCurr<=yCurr){
            VectorCheck(xCurr-yCurr+maxN-1,maxN-1,-1,-1,xCurr,yCurr);
        }else{
            VectorCheck(maxN-1,maxN-1-(xCurr-yCurr),-1,-1,xCurr,yCurr);
        }
        if(winner!=0) return true; else
        return false;
    }

    private void VectorCheck(int xx, int yy, int vx, int vy, int rx, int ry) {
        if(winner!=0) return;
        final int range=4;
        int i,j;
        int xbelow=rx-range*vx;
        int ybelow=ry-range*vy;
        int xabove=rx+range*vx;
        int yabove=ry+range*vy;
        String st="";
        i=xx; j=yy;
        while(!inside(i,xbelow,xabove) || !inside(j,ybelow,yabove)){
            i+=vx;
            j+=vy;
        }
        while (true){
            st=st+ String.valueOf(cellSeed[i][j]);
            if(st.length()==5){
                EvalEnd(st);
                st=st.substring(1,5);
            }
            i+=vx;
            j+=vy;
            if (!inBoard(i,j) || !inside(i,xbelow,xabove) || !inside(j,ybelow,yabove) || winner!=0){
                break;
            }
        }


    }

    private boolean inBoard(int i, int j) {
        if (i<0 || i>maxN-1 || j<0 || j>maxN-1)return false;
        return true;
    }

    private void EvalEnd(String st) {
        switch (st){
            case "11111":winner=1;break;
            case "22222":winner=2;break;
            default:break;
        }
    }

    private boolean inside(int i, int xbelow, int xabove) {
        return (i-xbelow)*(i-xabove)<=0;
    }

    private boolean noEmpty() {
        for(int i=0;i<maxN;i++){
            for(int j=0;j<maxN;j++)
                if(cellSeed[i][j]==0)
                    return false;
        }
        return true;
    }

    private void playerRedTurn() {
        tvturn.setText("Green");
        tvturn.setTextColor(Color.GREEN);
        isClicked=false;
    }


    private void initGame() {
        firstMove=true;
        winner=0;
        for(int i=0;i<maxN;i++){
            for(int j=0;j<maxN;j++){
                ivCell[i][j].setImageDrawable(drawCell[0]);
                cellSeed[i][j]=0;
            }
        }
    }

    private void loadResources() {
        drawCell[3]=context.getResources().getDrawable(R.drawable.cell_bg);
        drawCell[0]=null;
        drawCell[1]=context.getResources().getDrawable(R.drawable.star1);
        drawCell[2]=context.getResources().getDrawable(R.drawable.star2);
    }

    private boolean isClicked;

    @SuppressLint("NewApi")
    private void designBoardGame() {
        int sizeofCell=Math.round(ScreenWidth()/maxN);
        LinearLayout.LayoutParams lpRow=new LinearLayout.LayoutParams(sizeofCell*maxN,sizeofCell);
        LinearLayout.LayoutParams lpCell=new LinearLayout.LayoutParams(sizeofCell,sizeofCell);

        LinearLayout linBoardGame= (LinearLayout) findViewById(R.id.linBoardGame);

        for (int i=0;i<maxN;i++){
            LinearLayout linRow=new LinearLayout(context);

            for(int j=0;j<maxN;j++){
                ivCell[i][j]=new ImageView(context);
                ivCell[i][j].setBackground(drawCell[3]);

                final int x=i;
                final int y=j;
                ivCell[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(cellSeed[x][y]==0) {

                            if (turnPlay == 1 && !isClicked) {
                                isClicked = true;
                                xCurr = x;
                                yCurr = y;
                                makeAMove();

                            } else if (turnPlay == 2 && !isClicked) {
                                isClicked = true;
                                xCurr = x;
                                yCurr = y;
                                makeAMove();
                            }
                        }
                    }
                });
                linRow.addView(ivCell[i][j],lpCell);
            }
            linBoardGame.addView(linRow,lpRow);
        }
    }

    private float ScreenWidth() {
        Resources res=context.getResources();
        DisplayMetrics dm=res.getDisplayMetrics();
        return dm.widthPixels;
    }

}
