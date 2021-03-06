package com.example.kartik.recycler_view_json;

/**
 * Created by kartik on 5/2/18.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;



public class ListData {

    JSONObject jsonObject = null;

    private List<String> data;

    public ListData(Context c){
        data = new ArrayList<String>();

        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(c.getAssets().open("players.json")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                stringBuilder.append(mLine+"\n");
                //process line
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        String s = stringBuilder.toString().trim();



        try {
           jsonObject = new JSONObject(s.substring(s.indexOf("{"), s.lastIndexOf("}") + 1));
            JSONArray ques= (JSONArray) jsonObject.get("players");




            // take the elements of the json array

            for(int i=0; i<ques.length(); i++){

                System.out.println("The " + i + " element of the array: "+ques.get(i));
                JSONObject innerObj = (JSONObject) ques.getJSONObject(i);
                data.add(String.valueOf(innerObj.get("name")));

            }



            // take each value from the json array separately


        } catch (JSONException e) {
            e.printStackTrace();
        }





    }


    List<String> getList(){
        return data;
    }

    int getCount(){
        return data.size();
    }

    String getData(int position){
        return data.get(position);
    }

    public String displayContent(String s) {


        String res=null;

        try {

            JSONArray ques= (JSONArray) jsonObject.get("players");
            JSONArray bat = (JSONArray)jsonObject.get("batting");
            JSONArray bowl = (JSONArray)jsonObject.get("bowling");



            // take the elements of the json array

            for(int i=0; i<ques.length(); i++){


                JSONObject innerObj = (JSONObject) ques.getJSONObject(i);

                String name= String.valueOf(innerObj.get("name"));
                String type = String.valueOf(innerObj.get("type"));
                String id = String.valueOf(innerObj.get("playerId"));
                if(name.equalsIgnoreCase(s)){
                    Log.d("Found: ",s);


                    StringBuilder sb=new StringBuilder();
                    sb.append("Country: "+String.valueOf(innerObj.get("country"))+"\nType: "+String.valueOf(innerObj.get("type")));
                    if(type.equalsIgnoreCase("bowler"))
                    {
                        for(int j = 0; j < bowl.length(); j++)
                        {
                            JSONObject innerObj1 = (JSONObject) bowl.getJSONObject(j);
                            if(id.equalsIgnoreCase(String.valueOf(innerObj1.get("player_id"))))
                            {
                                sb.append("\nMatches: "+String.valueOf(innerObj1.get("matches"))+"\nWickets: "+String.valueOf(innerObj1.get("wickets_taken")+"\nEconomy: "+String.valueOf(innerObj1.get("economy"))+"\nStrike Rate: "+String.valueOf(innerObj1.get("strike_rate"))));
                            }
                        }
                    }
                    if(type.equalsIgnoreCase("batsman"))
                    {
                        for(int j = 0; j < bat.length(); j++)
                        {
                            JSONObject innerObj1 = (JSONObject) bat.getJSONObject(j);
                            if(id.equalsIgnoreCase(String.valueOf(innerObj1.get("player_id"))))
                            {
                                sb.append("\nMatches: "+String.valueOf(innerObj1.get("matches"))+"\nRuns: "+String.valueOf(innerObj1.get("runs")+"\nAverage: "+String.valueOf(innerObj1.get("avg"))+"\nStrike Rate: "+String.valueOf(innerObj1.get("strike_rate"))+"\nHigh Score: "+String.valueOf(innerObj1.get("high_score"))));
                            }
                        }
                    }
                    if(type.equalsIgnoreCase("wicketkeeper"))
                    {
                        for(int j = 0; j < bat.length(); j++)
                        {
                            JSONObject innerObj1 = (JSONObject) bat.getJSONObject(j);
                            if(id.equalsIgnoreCase(String.valueOf(innerObj1.get("player_id"))))
                            {
                                sb.append("\nMatches: "+String.valueOf(innerObj1.get("matches"))+"\nRuns: "+String.valueOf(innerObj1.get("runs")+"\nAverage: "+String.valueOf(innerObj1.get("avg"))+"\nStrike Rate: "+String.valueOf(innerObj1.get("strike_rate"))+"\nHigh Score: "+String.valueOf(innerObj1.get("high_score"))+"\nStumpings: "+String.valueOf(innerObj1.get("stumpings"))));
                            }
                        }
                    }
                    if(type.equalsIgnoreCase("allrounder"))
                    {
                        for(int j = 0; j < bat.length(); j++)
                        {
                            JSONObject innerObj1 = (JSONObject) bat.getJSONObject(j);
                            if(id.equalsIgnoreCase(String.valueOf(innerObj1.get("player_id"))))
                            {
                                sb.append("\nMatches: "+String.valueOf(innerObj1.get("matches"))+"\nRuns: "+String.valueOf(innerObj1.get("runs")+"\nAverage: "+String.valueOf(innerObj1.get("avg"))+"\nStrike Rate: "+String.valueOf(innerObj1.get("strike_rate"))+"\nHigh Score: "+String.valueOf(innerObj1.get("high_score"))));
                            }
                        }
                        for(int j = 0; j < bowl.length(); j++)
                        {
                            JSONObject innerObj1 = (JSONObject) bowl.getJSONObject(j);
                            if(id.equalsIgnoreCase(String.valueOf(innerObj1.get("player_id"))))
                            {
                                sb.append("\nWickets: "+String.valueOf(innerObj1.get("wickets_taken")+"\nEconomy: "+String.valueOf(innerObj1.get("economy"))+"\nStrike Rate: "+String.valueOf(innerObj1.get("strike_rate"))));
                            }
                        }
                    }
                    res=sb.toString();
                    break;

                }




            }

        } catch (JSONException e) {
            e.printStackTrace();
        }





        return res;


    }
}
