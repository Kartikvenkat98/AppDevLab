package com.example.akash.lab3;




import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener,fragmentLG.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment1 fragment1 = Fragment1.newInstance("1","2");
      getSupportFragmentManager().beginTransaction().replace(R.id.menuFRame,fragment1).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {





    }
}
