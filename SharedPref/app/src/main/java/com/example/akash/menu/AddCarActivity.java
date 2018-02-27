package com.example.akash.menu;

/**
 * Created by kartik on 26/2/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class AddCarActivity extends AppCompatActivity {

    EditText edt_addCar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        edt_addCar = findViewById(R.id.edt_addCar);


    }

    public void addCarToList(View view) {

        Intent passCar = new Intent(this,MainActivity.class);

        passCar.putExtra("car",edt_addCar.getText().toString());
        startActivity(passCar);
    }
}