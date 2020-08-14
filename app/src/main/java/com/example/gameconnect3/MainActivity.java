package com.example.gameconnect3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int gameStates[]={2,2,2,2,2,2,2,2,2};
    int [][]winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer=0; //0:green, 1:red
    boolean gameActive=true;
    public void dropIn(View view){

        ImageView counter=(ImageView) view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameStates[tappedCounter]==2 && gameActive) {
            gameStates[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.green);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameStates[winningPosition[0]] == gameStates[winningPosition[1]] && gameStates[winningPosition[1]] == gameStates[winningPosition[2]] && gameStates[winningPosition[0]] != 2) {
                    String winner = "";
                    if (activePlayer == 1)
                        winner = "Green";
                    else
                        winner = "Red";
//                    Toast.makeText(this, winner + " has Won", Toast.LENGTH_SHORT).show();
                    Button playButton=(Button) findViewById(R.id.playButton);
                    TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner+" has won!!");
                    playButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view)
    {
        Button playButton=(Button) findViewById(R.id.playButton);
        TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);

        playButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView)gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }
        Arrays.fill(gameStates, 2);
        activePlayer=0; //0:green, 1:red
        gameActive=true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}