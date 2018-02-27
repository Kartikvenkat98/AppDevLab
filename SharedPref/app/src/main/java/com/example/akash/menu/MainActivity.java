package com.example.akash.menu;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    List<String> carsList;
    TextView tv_cars;
    LinearLayout layout;
    // ListView lv_carslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //registerForContextMenu(tv_cars);
        populateInitialList();
        setUpSharedPreferences();
    }
    public void goToSettings(){
        Intent startSettingsActivity = new Intent(this,SettingsActivity.class);
        startActivity(startSettingsActivity);
    }

    public void setUpSharedPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        setItalic(sharedPreferences.getBoolean("italicize",true));
        setMyTheme(sharedPreferences.getString("theme","nature"));

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    private void setItalic(boolean b) {
        tv_cars = findViewById(R.id.tv_carList);
        SpannableString spannableString = new SpannableString(tv_cars.getText().toString());

        if(b)
            spannableString.setSpan(new StyleSpan(Typeface.ITALIC),0,spannableString.length(),0);
            //tvHello.setTypeface(null, Typeface.ITALIC);
        else
            spannableString.setSpan(new StyleSpan(Typeface.NORMAL),0,spannableString.length(),0);
        //tvHello.setTypeface(null,Typeface.NORMAL);
        tv_cars.setText(spannableString);
    }

    public void setMyTheme(String myTheme) {

        layout = findViewById(R.id.layout);

        if(myTheme.equals("nature")){

            layout.setBackgroundColor(getResources().getColor(R.color.nature));
        }
        else if(myTheme.equals("solar")){

            layout.setBackgroundColor(getResources().getColor(R.color.solar));
        }
        else if(myTheme.equals("techie")){

            layout.setBackgroundColor(getResources().getColor(R.color.techie));
        }
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if(s.equals("italicize"))
        {
            setItalic(sharedPreferences.getBoolean("italicize",true));
        }
        if(s.equals("theme")){
            setMyTheme(sharedPreferences.getString("theme","nature"));
        }

    }

    @Override
    protected void onDestroy() {
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }



    private void populateInitialList(){
        carsList = new ArrayList<String>();
        tv_cars=findViewById(R.id.tv_carList);

        if(getIntent().getStringExtra("car")!=null){
            carsList.add(getIntent().getStringExtra("car"));
        }

        StringBuilder sb = new StringBuilder();

        ListIterator<String> litr; //= carsList.listIterator();
        carsList.add("Honda City");
        carsList.add("Audi A6");
        carsList.add("Mini Countryman");


        litr = carsList.listIterator();
        while(litr.hasNext()){
            sb.append(litr.next());
            sb.append("\n");
        }
        Log.d("list", sb.toString());
        tv_cars.setText(sb.toString());

    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context menu");
        menu.add(0,1,0, "Red");
        menu.add(0,2,0,"Green");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        Log.d("TextSize:",item.getTitle().toString());
        if(item.getTitle().toString().equals("Red")){
            changeTextColor(true);
        }
        else {
            changeTextColor(false);
        }
        return super.onContextItemSelected(item);
    }

    private void changeTextColor(boolean b){
        //float currentSize = tv_cars.getTextSize();

        if(b){
            tv_cars.setTextColor(getResources().getColor(R.color.Red));
        }
        else{
            tv_cars.setTextColor(getResources().getColor(R.color.green));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.option_item_add:
                addItem();
                return true;

            case R.id.option_help:
                goToSettings();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addItem(){
        Intent addCarintent = new Intent(this,AddCarActivity.class);
        startActivity(addCarintent);
    }

    public void openHelp(){

    }



    public void openPopup(View view){
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(MainActivity.this,"Clicked "+ menuItem.getTitle()+ " item.",Toast.LENGTH_LONG).show();
                return true;
            }
        });
        popupMenu.show();
    }
}
