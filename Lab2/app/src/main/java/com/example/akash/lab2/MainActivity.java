package com.example.akash.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn[][] = new Button[3][3];
    TextView t1;
    boolean flag = false;
    int moves=0;
    Button r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn[0][0] = findViewById(R.id.button1);
        btn[0][1] = findViewById(R.id.button2);
        btn[0][2] = findViewById(R.id.button3);
        btn[1][0] = findViewById(R.id.button4);
        btn[1][1] = findViewById(R.id.button5);
        btn[1][2] = findViewById(R.id.button6);
        btn[2][0] = findViewById(R.id.button7);
        btn[2][1] = findViewById(R.id.button8);
        btn[2][2] = findViewById(R.id.button9);
        r=findViewById(R.id.button);
        t1 = findViewById(R.id.textView);
        moves=0;

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                            btn[i][j].setEnabled(true);
                            btn[i][j].setText("");
                            t1.setText("");
                            moves=0;

                    }
                }

            }
        });


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btn[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button temp = findViewById(v.getId());
                        temp.setEnabled(false);
                        if (!flag) {
                            temp.setText("X");
                            moves++;

                        } else {
                            temp.setText("O");
                            moves++;
                        }

                        if (!Iswinner()) {

                            if(moves==9){
                                t1.setText("Match Draw!!");
                            }

                            flag = !flag;
                        }
                    }
                });
            }
        }
    }




        public boolean Iswinner() {


            if ((btn[0][0].getText() == "O" && btn[1][0].getText() == "O" && btn[2][0].getText() == "O")
                    || (btn[0][1].getText() == "O" && btn[1][1].getText() == "O" && btn[2][1].getText() == "O")
                    || (btn[0][2].getText() == "O" && btn[1][2].getText() == "O" && btn[2][2].getText() == "O")
                    || (btn[0][0].getText() == "O" && btn[0][1].getText() == "O" && btn[0][2].getText() == "O")
                    || (btn[1][0].getText() == "O" && btn[1][1].getText() == "O" && btn[1][2].getText() == "O")
                    || (btn[2][0].getText() == "O" && btn[2][1].getText() == "O" && btn[2][2].getText() == "O")
                    || (btn[0][0].getText() == "O" && btn[1][1].getText() == "O" && btn[2][2].getText() == "O")
                    || (btn[0][2].getText() == "O" && btn[1][1].getText() == "O" && btn[2][0].getText() == "O")) {


                t1.setText("Player 2 Wins");
                disBut();
                return true;


            }

            if ((btn[0][0].getText() == "X" && btn[1][0].getText() == "X" && btn[2][0].getText() == "X")
                    || (btn[0][1].getText() == "X" && btn[1][1].getText() == "X" && btn[2][1].getText() == "X")
                    || (btn[0][2].getText() == "X" && btn[1][2].getText() == "X" && btn[2][2].getText() == "X")
                    || (btn[0][0].getText() == "X" && btn[0][1].getText() == "X" && btn[0][2].getText() == "X")
                    || (btn[1][0].getText() == "X" && btn[1][1].getText() == "X" && btn[1][2].getText() == "X")
                    || (btn[2][0].getText() == "X" && btn[2][1].getText() == "X" && btn[2][2].getText() == "X")
                    || (btn[0][0].getText() == "X" && btn[1][1].getText() == "X" && btn[2][2].getText() == "X")
                    || (btn[0][2].getText() == "X" && btn[1][1].getText() == "X" && btn[2][0].getText() == "X")) {


                t1.setText("Player 1 Wins");
                disBut();
                return true;


            }
            return false;
        }



        public void disBut(){

                    for(int i=0;i<3;i++){
                        for(int j=0;j<3;j++){
                            if(btn[i][j].isEnabled()){
                                btn[i][j].setEnabled(false);
                            }
                        }
                    }
    }




    }
