package com.example.kartik.sqlite;

import android.app.ListActivity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by kartik on 19/2/18.
 */

    public class SimpleWidgetProvider extends AppWidgetProvider{

        @Override
        public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
            final int count = appWidgetIds.length;

            for (int i = 0; i < count; i++) {
                int widgetId = appWidgetIds[i];
                String number = String.format("%03d", (new Random().nextInt(900) + 100));

                StudentRepo repo = new StudentRepo(context);

                ListView lv;

                StringBuilder sb = new StringBuilder();

                ArrayList<HashMap<String, String>> studentList =  repo.getStudentList();
                if(studentList.size()!=0) {
                    for(i = 0; i < studentList.size(); i++) {
                        for(Map.Entry<String, String> entry : studentList.get(i).entrySet()) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            sb.append(key + ":" + value + "\n");
                    }
                }

                }

                RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                        R.layout.simple_widget);
                remoteViews.setTextViewText(R.id.textView, sb.toString());

                Intent intent = new Intent(context, SimpleWidgetProvider.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                        0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.actionButton, pendingIntent);
                appWidgetManager.updateAppWidget(widgetId, remoteViews);
            }
        }
    }


